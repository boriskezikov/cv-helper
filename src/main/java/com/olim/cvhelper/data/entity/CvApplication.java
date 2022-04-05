package com.olim.cvhelper.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "cv_applications")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvApplication extends AbstractEntity {


    private String fullName;

    private String telegramUsername;

    private String linkedInLink;

    private String cvLink;

    private Long chatId;

    @Enumerated(EnumType.STRING)
    private CvApplicationStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee")
    private User assignee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
