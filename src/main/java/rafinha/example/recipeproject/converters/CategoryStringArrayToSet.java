package rafinha.example.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.commands.CategoryCommand;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryStringArrayToSet implements Converter<String[], Set<CategoryCommand>> {

    @Override
    public Set<CategoryCommand> convert(String[] source) {
        Set<CategoryCommand> setToReturn = new HashSet<>();
        for(String category : source) {
            CategoryCommand categoryCommand = new CategoryCommand();
            int indexOfEquals = category.indexOf('=');
            String id = category.substring(0, indexOfEquals);
            String desc = category.substring(indexOfEquals + 1);

            categoryCommand.setId(Long.valueOf(id));
            categoryCommand.setCategoryName(desc);

            setToReturn.add(categoryCommand);

        }
        return setToReturn;
    }
}
