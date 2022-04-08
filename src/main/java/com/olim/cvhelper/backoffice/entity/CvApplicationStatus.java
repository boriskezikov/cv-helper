package com.olim.cvhelper.backoffice.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum CvApplicationStatus {

    OPEN("contrast primary"),
    IN_WORK("primary"),
    CLOSED("success primary"),
    REOPENED("contrast primary");


    private final String color;

    public static List<String> getValues() {
        return Stream.of(CvApplicationStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
