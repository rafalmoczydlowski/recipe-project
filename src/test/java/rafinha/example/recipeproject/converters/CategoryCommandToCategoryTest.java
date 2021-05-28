package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.CategoryCommand;
import rafinha.example.recipeproject.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    public static final String CATEGORY_NAME = "Category Name";
    public static final Long ID_VALUE = Long.valueOf(1L);

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        CategoryCommand command = new CategoryCommand(); // tworzymy Command Object
        command.setId(ID_VALUE);
        command.setCategoryName(CATEGORY_NAME);

        Category category = converter.convert(command); // konwertujemy CommandObject na główny obiekt

        assertNotNull(category);
        assertEquals(ID_VALUE, category.getId()); // sprawdzamy czy głowny obiekt ma ten sam ID co zadeklarowany dla Command Object
        assertEquals(CATEGORY_NAME, category.getCategoryName()); // sprawdzamy czy głowny obiekt ma tą samą kategorię co zadeklarowany dla Command Object
    }
}