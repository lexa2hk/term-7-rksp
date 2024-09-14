package org.example;

import io.rsocket.RSocket;
import io.rsocket.core.RSocketClient;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RSocketClientApp {
    public RSocketClient createClient() {
        Mono<RSocket> rSocketMono = RSocketConnector.connectWith(TcpClientTransport.create("localhost", 7000));

        // Request-Response
        rSocketMono.flatMap(rSocket -> rSocket.requestResponse(DefaultPayload.create("Hello")))
                .doOnNext(payload -> System.out.println("Response: " + payload.getDataUtf8()))
                .block();

        // Fire-and-Forget
        rSocketMono.flatMap(rSocket -> rSocket.fireAndForget(DefaultPayload.create("Fire-and-Forget message")))
                .block();

        // Request-Stream
        rSocketMono.flatMapMany(rSocket -> rSocket.requestStream(DefaultPayload.create("Stream Request")))
                .doOnNext(payload -> System.out.println("Stream: " + payload.getDataUtf8()))
                .blockLast();

        // Channel
        rSocketMono.flatMapMany(rSocket -> rSocket.requestChannel(Flux.just(
                        DefaultPayload.create("Channel Message 1"),
                        DefaultPayload.create("Channel Message 2")
                ))).doOnNext(payload -> System.out.println("Channel response: " + payload.getDataUtf8()))
                .blockLast();
        return RSocketClient.from(rSocketMono);
    }
}

