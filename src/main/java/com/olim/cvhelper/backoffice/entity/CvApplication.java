package com.olim.cvhelper.backoffice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "cv_applications")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE cv_applications SET to_remove = true WHERE id=?")
@Where(clause = "to_remove=false")
public class CvApplication extends AbstractEntity {


    private String fullName;

    private String telegramUsername;

    private String linkedInLink;

    private String cvLink;

    private String question;

    private String profession;

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
