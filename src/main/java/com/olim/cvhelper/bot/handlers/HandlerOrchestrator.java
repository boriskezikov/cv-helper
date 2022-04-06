package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.config.BotState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class HandlerOrchestrator {

    private final List<AbstractHandler> handlers;

    public AbstractHandler getStateHandler(Long stateId) {
        return handlers.stream()
                .filter(h -> h.getClass()
                        .isAnnotationPresent(BotState.class))
                .filter(h -> Stream.of(h.getClass()
                        .getAnnotation(BotState.class)
                        .orderId()
                        .getOrder()).anyMatch(order -> Objects.equals(order, stateId)))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }
}
