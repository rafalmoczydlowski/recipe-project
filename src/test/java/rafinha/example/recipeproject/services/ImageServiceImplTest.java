package rafinha.example.recipeproject.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void saveImageFile() throws Exception {
        // given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt",
                "text/plain", "Rafinha Example".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        ArgumentCaptor<Recipe> recipeArgumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        // when
        imageService.saveImageFile(id, multipartFile);

        // then
        verify(recipeRepository, times(1)).findById(anyLong());
        // verifying that the RecipeRepository's save method is called one time and
        verify(recipeRepository, times(1)).save(recipeArgumentCaptor.capture());
        // capture argument going to give a savedRecipe
        Recipe savedRecipe = recipeArgumentCaptor.getValue();
        // check to see if the bytes match
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }
}