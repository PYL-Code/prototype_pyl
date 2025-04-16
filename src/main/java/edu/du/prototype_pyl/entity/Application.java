package edu.du.prototype_pyl.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long campaignId;

    @Column(nullable = false)
    private Long reviewerId;

    @Column(nullable = false)
    private String campaignTitle;

    @Column(nullable = false)
    private String reviewerNickname;

    @Column(nullable = false)
    private String phone;

    @Column
    private String snsUrl;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    public enum ApplicationStatus {
        신청대기, 당첨, 미당첨, 신청취소
    }
}
