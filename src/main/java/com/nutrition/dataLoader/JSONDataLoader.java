package com.nutrition.dataLoader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.dto.FoodDTO;
import com.nutrition.model.Food;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Component
public class JSONDataLoader implements DataLoader {

    @Override
    public List<Food> load(File file) {
        ObjectMapper mapper = new ObjectMapper();
        List<Food> foods = new ArrayList<>();

        try {
            JsonNode root = mapper.readTree(file);

            for (JsonNode node : root) {
                FoodDTO dto = new FoodDTO(
                        node.get("name").asText(),
                        node.get("calories").asInt(),
                        node.get("totalFat").asDouble(),
                        node.get("caffeine").asText()
                );
                if (checkValidation(dto)) {
                    foods.add(new Food(dto));
                }

            }

        } catch (IOException e) {
            throw new RuntimeException("Error loading from JSON File, please review.");
        }

        return foods;
    }

    private boolean checkValidation(FoodDTO dto){
        return isValidNode(dto);
    }

    private static boolean isValidNode(FoodDTO dto) {
        return StringUtils.isNotBlank(dto.name()) &&
                dto.calories() != null  &&
                dto.totalFat() != null &&
                StringUtils.isNotBlank(dto.caffeine());
    }
}
