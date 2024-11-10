package org.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "flightsServiceClient", url = "${user.flights.service.url}")
public interface FlightsServiceClient {

    @GetMapping("/api/user/{userId}/scheduled-flights")
    UserFullname getUserScheduledFlights(@PathVariable("userId") Long userId);
}

