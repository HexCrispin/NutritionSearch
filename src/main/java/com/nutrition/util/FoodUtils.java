package com.nutrition.util;

import com.nutrition.model.Food;

public class FoodUtils {

    public static Food.FatRating getFatRating(Double totalFat) {
        if (totalFat >= 17.5) return Food.FatRating.HIGH;
        if (totalFat <= 3) return Food.FatRating.LOW;
        return Food.FatRating.MEDIUM;
    }
}