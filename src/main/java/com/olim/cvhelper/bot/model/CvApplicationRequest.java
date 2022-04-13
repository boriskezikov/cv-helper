package com.olim.cvhelper.bot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvApplicationRequest implements Serializable {


    private String fullName;

    private String telegramUsername;

    private String linkedInLink;

    private String cvLink;

    private String question;

    private String profession;

}
