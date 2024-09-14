package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface H2DbEntityRepository extends JpaRepository<H2DbEntity, Long> {
}