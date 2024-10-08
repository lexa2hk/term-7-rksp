package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select u from UserRole u where u.userId = ?1")
    UserRole findByUserId(Long userId);
}