package rafinha.example.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import rafinha.example.recipeproject.commands.CategoryCommand;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategorySingleStringToSet implements Converter<String, Set<CategoryCommand>> {

    @Override
    public Set<CategoryCommand> convert(String source) {
        Set<CategoryCommand> setToReturn = new HashSet<>();
        int indexOfEquals = source.indexOf('=');
        Long id = Long.valueOf(source.substring(0, indexOfEquals));
        String desc = source.substring(indexOfEquals + 1);

        CategoryCommand command = new CategoryCommand();
        command.setId(id);
        command.setCategoryName(desc);

        setToReturn.add(command);

        return setToReturn;
    }
}
