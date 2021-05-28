package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.CategoryCommand;
import rafinha.example.recipeproject.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String CATEGORY_NAME = "Category Name";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyParameter() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setCategoryName(CATEGORY_NAME);

        CategoryCommand categoryCommand = converter.convert(category);

        assertNotNull(categoryCommand);
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(CATEGORY_NAME, categoryCommand.getCategoryName());
    }
}