package com.olim.cvhelper.bot.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "states")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)  // See (1)
public class State {

    @Id
    private Long userId;
    private Long stateId;

    @Type(type = "jsonb") // See (2)
    @Column(name = "stateData", columnDefinition = "jsonb")
    private CvApplicationRequest stateData;
}
