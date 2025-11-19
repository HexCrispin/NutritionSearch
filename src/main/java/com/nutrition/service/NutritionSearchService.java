package com.nutrition.service;

import com.nutrition.dto.NutritionSearchRequest;
import com.nutrition.dto.Sort;
import com.nutrition.dto.SortField;
import com.nutrition.dto.SortOrder;
import com.nutrition.model.Food;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;

public final class NutritionSearchService {

    private static final EnumMap<SortField, Comparator<Food>> FIELD_COMPARATORS = new EnumMap<>(SortField.class);

    static {
        FIELD_COMPARATORS.put(SortField.CALORIES, Comparator.comparing(
                Food::calories, Comparator.naturalOrder()));
        FIELD_COMPARATORS.put(SortField.NAME, Comparator.comparing(
                Food::name, String.CASE_INSENSITIVE_ORDER));
    }

    private final List<Food> foods;

    public NutritionSearchService(List<Food> foods) {
        this.foods = List.copyOf(foods);
    }

    public List<Food> searchNutrition(NutritionSearchRequest request) {
        return foods.stream()
                .filter(isValidFoodItemForSearchCriteria(request))
                .sorted(buildComparator(request))
                .limit(request.limit())
                .toList();
    }

    private Comparator<Food> buildComparator(NutritionSearchRequest request) {
        return request.sorts().stream()
                .map(this::getComparator)
                .reduce(Comparator::thenComparing)
                .orElse((h1, h2) -> 0);
    }

    private Comparator<Food> getComparator(Sort sort) {
        var comparator = FIELD_COMPARATORS.get(sort.field());
        return sort.order() == SortOrder.ASC ? comparator : comparator.reversed();
    }

    private Predicate<Food> isValidFoodItemForSearchCriteria(NutritionSearchRequest request) {
        Predicate<Food> predicate = food -> true;

        if( request.minCalories() != null) {
            predicate = predicate.and(food -> food.calories() >= request.minCalories());
        }

        if( request.maxCalories() != null) {
            predicate = predicate.and(food -> food.calories() <= request.maxCalories());
        }

        if (request.fatRating() != null) {
            predicate = predicate.and(food -> food.fatRating() == request.fatRating());
        }

        return predicate;
    }
}