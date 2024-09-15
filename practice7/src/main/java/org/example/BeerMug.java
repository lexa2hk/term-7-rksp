package org.example;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("beer_mugs")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerMug {

    @Id
    private Long id;
    private Long userId;
    private LocalDateTime readyTime;
    private String mugStatus;

    enum Status {
        POURING,
        READY
    }
}
