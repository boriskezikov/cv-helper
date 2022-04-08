package com.olim.cvhelper.bot.model;

import lombok.Getter;

@Getter
public enum StateOrder {

    USERNAME(1L),
    LINKEDIN_LINK(2L),
    CV_LINK(3L),
    WAIT_FOR_HELP(4L);

    private final Long order;

    StateOrder(Long order) {
        this.order = order;
    }
}
