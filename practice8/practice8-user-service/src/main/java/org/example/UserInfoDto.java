package org.example;

public record UserInfoDto(
        UserFlights flights,
        UserRole userRole
) {
}
