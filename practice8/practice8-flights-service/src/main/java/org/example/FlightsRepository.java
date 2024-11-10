package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightsRepository extends JpaRepository<UserFlights, Long> {
    @Query("select u from UserFlights u where u.userId = ?1")
    UserFlights findByUserId(Long userId);
}