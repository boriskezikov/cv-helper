package com.olim.cvhelper.backoffice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
