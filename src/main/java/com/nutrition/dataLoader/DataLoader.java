package com.nutrition.dataLoader;

import com.nutrition.model.Food;

import java.io.File;
import java.util.List;

public interface DataLoader {
    List<Food> load(File file);
}
