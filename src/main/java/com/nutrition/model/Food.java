package com.nutrition.model;

import com.nutrition.dto.FoodDTO;
import com.nutrition.util.FoodUtils;

public record Food(
    String name,
    Integer calories,
    Double totalFat,
    FatRating fatRating,
    String caffeine
) {
    public enum FatRating {
        LOW,
        MEDIUM,
        HIGH
    }

    public Food(FoodDTO dto) {
        this(
                dto.name(),
                dto.calories(),
                dto.totalFat(),
                FoodUtils.getFatRating(dto.totalFat()),
                dto.caffeine()
        );
    }
}
