package rafinha.example.recipeproject.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.commands.CategoryCommand;
import rafinha.example.recipeproject.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null)
            return null;

        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setCategoryName(categoryCommand.getCategoryName());
        return category;
    }
}
