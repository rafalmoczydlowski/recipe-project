package rafinha.example.recipeproject.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rafinha.example.recipeproject.commands.UnitOfMeasureCommand;
import rafinha.example.recipeproject.domain.UnitOfMeasure;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID_VALUE = Long.valueOf(1L);
    public static final String UOM = "Unit of measure";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testWithNullParameter() {
        assertNull(converter.convert(null));
    }

    @Test
    void testWithEmptyParameter() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_VALUE);
        unitOfMeasure.setUom(UOM);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID_VALUE, unitOfMeasureCommand.getId());
        assertEquals(UOM, unitOfMeasureCommand.getUom());
    }
}