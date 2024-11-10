package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFlightsControllerUnitTest {

    @Mock
    private FlightsRepository flightsRepository;
    @Mock
    private UserClient userClient;
    @InjectMocks
    private FlightsController flightsController;

    @Test
    void testGetUserFullname() {
        when(userClient.getAllUsers())
                .thenReturn(List.of(
                        User.builder()
                                .id(4L)
                                .build(),
                        User.builder()
                                .id(5L)
                                .build(),
                        User.builder()
                                .id(6L)
                                .build()
                ));
        when(flightsRepository.findByUserId(anyLong()))
                .thenReturn(UserFlights.builder()
                        .flightCode("SU1763")
                        .build());

        UserFlights actualFullname = flightsController.getUserScheduledFlights(4L);

        assertThat(actualFullname.getFlightCode()).isEqualTo("SU1763");
    }
}