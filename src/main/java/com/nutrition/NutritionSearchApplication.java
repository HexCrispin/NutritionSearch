package com.nutrition;

import com.nutrition.dataLoader.DataLoader;
import com.nutrition.dataLoader.DataLoaderFactory;
import com.nutrition.exception.CsvFileLoadingException;
import com.nutrition.model.Food;
import com.nutrition.service.NutritionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class NutritionSearchApplication {

    @Value("${nutrition-search.data.file.name}")
    private String nutritionDataFile;

    @Autowired
    private DataLoaderFactory dataLoaderFactory;

    @Bean
    public NutritionSearchService nutritionSearchService() {
        File file = readFile();
        DataLoader loader = dataLoaderFactory.getLoader(file);
        List<Food> foods = loader.load(file);
        return new NutritionSearchService(foods);
    }

    @Bean
    public File readFile() {
        try {
            var nutritionData = new ClassPathResource(nutritionDataFile);
            return nutritionData.getFile();
        } catch (IOException exception) {
            throw new CsvFileLoadingException(exception);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NutritionSearchApplication.class, args);
    }
}
