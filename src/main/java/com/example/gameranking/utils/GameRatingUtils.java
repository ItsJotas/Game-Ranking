package com.example.gameranking.utils;

import com.example.gameranking.exception.customized.BadRequestException;
import com.example.gameranking.model.GameRating;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameRatingUtils {

    private static List<BigDecimal> extractValues(GameRating gameRating) {
        List<BigDecimal> values = new ArrayList<>();

        for (Method method : GameRating.class.getMethods()) {
            if (method.getName().startsWith("get") && Objects.equals(method.getParameterCount(), 0) &&
                    method.getReturnType().equals(BigDecimal.class)) {
                try {
                    BigDecimal value = (BigDecimal) method.invoke(gameRating);
                    if (value != null) {
                        values.add(value);
                    }
                } catch (Exception e) {
                    throw new BadRequestException("Error when calculating the average.");
                }
            }
        }

        return values;
    }

    public static BigDecimal calculateAverage(GameRating gameRating) {

        List<BigDecimal> values = extractValues(gameRating);
        BigDecimal sum = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        long quantity = values.size();

        return quantity > 0
                ? sum.divide(BigDecimal.valueOf(quantity), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
    }
}
