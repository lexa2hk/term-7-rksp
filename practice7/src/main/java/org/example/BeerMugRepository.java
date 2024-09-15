package org.example;

import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerMugRepository extends ReactiveCrudRepository<BeerMug, Long> {
    Mono<Void> deleteByUserId(Long userId);
}
