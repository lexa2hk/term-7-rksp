package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Semaphore;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BarServiceTest {

    @Mock
    private BeerMugRepository beerMugRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BarService barService;

    private final Semaphore semaphore = new Semaphore(3);

    @BeforeEach
    void setUp() {
        semaphore.drainPermits();
        semaphore.release(3);
    }

    @Test
    void testPourOneBeer() {
        Long userId = 1L;
        BeerMug beerMug = new BeerMug();
        beerMug.setId(1L);
        beerMug.setUserId(userId);
        beerMug.setMugStatus(BeerMug.Status.READY.name());
        beerMug.setReadyTime(LocalDateTime.now().plusSeconds(10));

        when(beerMugRepository.save(any(BeerMug.class))).thenReturn(Mono.just(beerMug));

        StepVerifier.create(barService.pourOneBeer(userId))
                .expectSubscription()
                .expectNext(beerMug.getId())
                .expectComplete()
                .verify();

        verify(beerMugRepository, times(1)).save(any(BeerMug.class));
    }

    @Test
    void testPourManyBeers() {
        Long userId = 1L;
        int count = 3;
        BeerMug beerMug = new BeerMug();
        beerMug.setId(1L);
        beerMug.setUserId(userId);
        beerMug.setMugStatus(BeerMug.Status.READY.name());
        beerMug.setReadyTime(LocalDateTime.now().plusSeconds(10));

        when(beerMugRepository.save(any(BeerMug.class))).thenReturn(Mono.just(beerMug));

        StepVerifier.create(barService.pourManyBeers(userId, count))
                .expectSubscription()
                .expectNextCount(count)
                .expectComplete()
                .verify();

        verify(beerMugRepository, times(count)).save(any(BeerMug.class));
    }

    @Test
    void testGetBeerMugs() {
        Long userId = 1L;
        BeerMug beerMug = new BeerMug();
        beerMug.setId(1L);
        beerMug.setUserId(userId);
        beerMug.setMugStatus(BeerMug.Status.READY.name());
        beerMug.setReadyTime(LocalDateTime.now().plusSeconds(10));

        when(beerMugRepository.findAllById(List.of(1L))).thenReturn(Flux.just(beerMug));

        StepVerifier.create(barService.getBeerMugs(List.of(1L), userId))
                .expectSubscription()
                .expectNext(beerMug)
                .expectComplete()
                .verify();

        verify(beerMugRepository, times(1)).findAllById(anyList());
    }

    @Test
    void testGetBeerMugsNotFound() {
        Long userId = 1L;

        when(beerMugRepository.findAllById(List.of(1L))).thenReturn(Flux.empty());

        StepVerifier.create(barService.getBeerMugs(List.of(1L), userId))
                .expectError(IllegalStateException.class)
                .verify();

        verify(beerMugRepository, times(1)).findAllById(anyList());
    }

    @Test
    void testEnterBar() {
        User user = User.builder().id(1L).username("posetitel").build();

        when(userRepository.save(any(User.class))).thenReturn(Mono.just(user));

        StepVerifier.create(barService.enterBar())
                .expectSubscription()
                .expectNext(user)
                .expectComplete()
                .verify();

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testExitBar() {
        Long userId = 1L;

        when(beerMugRepository.deleteByUserId(userId)).thenReturn(Mono.empty());
        when(userRepository.deleteById(userId)).thenReturn(Mono.empty());

        StepVerifier.create(barService.exitBar(userId))
                .expectSubscription()
                .expectComplete()
                .verify();

        verify(beerMugRepository, times(1)).deleteByUserId(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
