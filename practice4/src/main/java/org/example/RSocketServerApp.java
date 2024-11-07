package org.example;

import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RSocketServerApp {
    private final RSocketHandler rSocketHandler;

    public void createServer() {
        RSocketServer.create(SocketAcceptor.with(rSocketHandler))
                .bind(TcpServerTransport.create("localhost", 7000))
                .block();
    }
}


