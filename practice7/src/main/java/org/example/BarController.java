package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/bar")
@RequiredArgsConstructor
@RestControllerAdvice
public class BarController {

    private final BarService barService;

    @PostMapping("/pour-one")
    public Mono<Long> pourOneBeer(@RequestParam Long userId) {
        return barService.pourOneBeer(userId);
    }

    @PostMapping("/pour-many")
    public Flux<Long> pourManyBeers(@RequestParam Long userId, @RequestParam int count) {
        return barService.pourManyBeers(userId, count);
    }

    @GetMapping("/mugs")
    public Flux<BeerMug> getBeerMugs(@RequestParam List<Long> mugIds, @RequestParam Long userId) {
        return barService.getBeerMugs(mugIds, userId);
    }

    @PostMapping("/enter")
    public Mono<User> enterBar() {
        return barService.enterBar();
    }

    @DeleteMapping("/exit")
    public Mono<Void> exitBar(@RequestParam Long userId) {
        return barService.exitBar(userId);
    }

    @ExceptionHandler(IllegalStateException.class)
    public Mono<ProblemDetail> handleException(IllegalStateException e) {
        return Mono.just(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage()));
    }
}
