package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFullnameRepository extends JpaRepository<UserFullname, Long> {
    @Query("select u from UserFullname u where u.userId = ?1")
    UserFullname findByUserId(Long userId);
}