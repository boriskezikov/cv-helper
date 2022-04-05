package com.olim.cvhelper.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvApplication extends AbstractEntity {

    private String cvLink;
    @Enumerated(EnumType.STRING)
    private CvApplicationStatus status;
}
