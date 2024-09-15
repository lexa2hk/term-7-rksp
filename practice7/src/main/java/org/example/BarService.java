package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
public class BarService {

    private final BeerMugRepository beerMugRepository;
    private final UserRepository userRepository;

    private final Semaphore pouringSemaphore = new Semaphore(3);

    public Mono<Long> pourOneBeer(Long userId) {
        return Mono.fromCallable(() -> {
                    pouringSemaphore.acquire();
                    BeerMug mug = new BeerMug();
                    mug.setUserId(userId);
                    mug.setMugStatus(BeerMug.Status.READY.name());
                    mug.setReadyTime(LocalDateTime.now().plusSeconds(10));
                    return beerMugRepository.save(mug);
                })
                .delayElement(Duration.ofSeconds(10))
                .publishOn(Schedulers.boundedElastic())
                .map(mug -> Objects.requireNonNull(mug.block()).getId())
                .doFinally(signalType -> pouringSemaphore.release());
    }

    public Flux<Long> pourManyBeers(Long userId, int count) {
        return Flux.range(0, count)
                .flatMap(i -> pourOneBeer(userId));
    }

    public Flux<BeerMug> getBeerMugs(List<Long> mugIds, Long userId) {
        return beerMugRepository.findAllById(mugIds)
                .filter(mug -> mug.getUserId().equals(userId))
                .switchIfEmpty(Mono.error(new IllegalStateException("Beer not ready")))
                .flatMap(Mono::just);
    }

    public Mono<User> enterBar() {
        return userRepository.save(User.builder().username("posetitel").build());
    }

    public Mono<Void> exitBar(Long userId) {
        return beerMugRepository.deleteByUserId(userId)
                .then(userRepository.deleteById(userId));
    }
}
