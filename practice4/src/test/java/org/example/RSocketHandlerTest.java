package org.example;

import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RSocketHandlerTest {

    @Mock
    private H2DbEntityRepository repository;

    @InjectMocks
    private RSocketHandler rSocketHandler;

    @Test
    public void testFireAndForget() {
        Payload testPayload = DefaultPayload.create("Test Fire-and-Forget");

        Mono<Void> result = rSocketHandler.fireAndForget(testPayload);

        StepVerifier.create(result)
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any(H2DbEntity.class));
    }

    @Test
    public void testRequestResponse() {
        Payload testPayload = DefaultPayload.create("Test Request-Response");

        Mono<Payload> result = rSocketHandler.requestResponse(testPayload);

        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getDataUtf8().equals("Response to: Test Request-Response"))
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any(H2DbEntity.class));
    }

    @Test
    public void testRequestStream() {
        Payload testPayload = DefaultPayload.create("Test Request-Stream");

        Flux<Payload> result = rSocketHandler.requestStream(testPayload);

        StepVerifier.create(result)
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Stream item 1"))
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Stream item 2"))
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Stream item 3"))
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Stream item 4"))
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Stream item 5"))
                .expectComplete()
                .verify();

        verify(repository, times(1)).save(any(H2DbEntity.class));
    }

    @Test
    public void testRequestChannel() {
        Flux<Payload> payloads = Flux.just(
                DefaultPayload.create("Test Channel 1"),
                DefaultPayload.create("Test Channel 2")
        );

        Flux<Payload> result = rSocketHandler.requestChannel(payloads);

        StepVerifier.create(result)
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Received: Test Channel 1"))
                .expectNextMatches(payload -> payload.getDataUtf8().equals("Received: Test Channel 2"))
                .expectComplete()
                .verify();

        verify(repository, times(2)).save(any(H2DbEntity.class));
    }
}
