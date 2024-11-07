package org.example;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@Component
@RequiredArgsConstructor
public class RSocketHandler implements RSocket {
    private final H2DbEntityRepository repository;

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        repository.save(H2DbEntity.builder()
                .payload(payload.getDataUtf8())
                .build());
        System.out.println("Received Fire-and-Forget: " + payload.getDataUtf8());
        return Mono.empty();
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        repository.save(H2DbEntity.builder()
                .payload(payload.getDataUtf8())
                .build());
        return Mono.just(DefaultPayload.create("Response to: " + payload.getDataUtf8()));
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        repository.save(H2DbEntity.builder()
                .payload(payload.getDataUtf8())
                .build());
        return Flux.range(1, 5)
                .map(i -> DefaultPayload.create("Stream item " + i));
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return Flux.from(payloads)
                .map(payload -> {
                    repository.save(H2DbEntity.builder()
                            .payload(payload.getDataUtf8())
                            .build());
                    return DefaultPayload.create("Received: " + payload.getDataUtf8());
                });
    }
}

