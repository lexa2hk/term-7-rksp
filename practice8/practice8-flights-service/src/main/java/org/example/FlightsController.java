package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class FlightsController {

    private final FlightsRepository flightsRepository;
    private final UserClient userClient;

    @GetMapping("/{userId}/scheduled-flights")
    public UserFlights getUserScheduledFlights(@PathVariable("userId") Long userId) {
        flightsRepository.deleteAll();
        List<User> allUsers = userClient.getAllUsers();
        flightsRepository.saveAll(
                List.of(
                        UserFlights.builder()
                                .userId(allUsers.get(0).getId())
                                .flightCode("SU1763")
                                .build(),
                        UserFlights.builder()
                                .userId(allUsers.get(1).getId())
                                .flightCode("SU1763")
                                .build(),
                        UserFlights.builder()
                                .userId(allUsers.get(2).getId())
                                .flightCode("SU1763")
                                .build()
                )
        );
        return flightsRepository.findByUserId(userId + 3);
    }
}
