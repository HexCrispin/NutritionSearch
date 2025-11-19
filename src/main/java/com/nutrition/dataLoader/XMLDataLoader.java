package com.nutrition.dataLoader;

import com.nutrition.model.Food;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class XMLDataLoader implements DataLoader {
    @Override

    public List<Food> load(File file) {
        throw new UnsupportedOperationException("XML loader not implemented yet");
    }
}
