package edu.du.prototype_pyl.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private LocalDate applicationDeadline;

    @Column(nullable = false)
    private LocalDate reviewDeadline;

    @Column(nullable = false)
    private int quota;

    @Column(nullable = false)
    private boolean recruitActive;

    @Column(nullable = false)
    private boolean reviewActive;
}
