package com.olim.cvhelper.bot.model;

import com.olim.cvhelper.bot.util.TextConstants;
import lombok.Getter;

@Getter
public enum StateOrder {

    USERNAME(1L),
    PROFESSION(2L),
    LINKEDIN_LINK(3L),
    CV_LINK(4L),
    QUESTION(5L),
    WAIT_FOR_HELP(6L);

    private final Long order;
    private TextConstants text;

    StateOrder(Long order) {
        this.order = order;
    }

    public StateOrder getNext(StateOrder stateOrder) {
        return StateOrder.values()[(stateOrder.ordinal() + 1) % StateOrder.values().length];

    }
}
