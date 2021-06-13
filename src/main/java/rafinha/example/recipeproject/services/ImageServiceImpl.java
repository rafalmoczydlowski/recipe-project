package rafinha.example.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rafinha.example.recipeproject.domain.Recipe;
import rafinha.example.recipeproject.repositories.RecipeRepository;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        try {
            // get the Recipe obj out of the repo [going to saving against an exisitng recipe]
            Recipe recipe = recipeRepository.findById(id).get();
            // convert to wrapper class of a Byte into Byte object
            Byte[] byteObj = new Byte[file.getBytes().length];

            // set up iterator to iterate over that and
            // convert over the Byte Object from Java primitive to the wrapper object
            int i = 0;

            for (byte b : file.getBytes())
                byteObj[i++] = b;

            recipe.setImage(byteObj);
            recipeRepository.save(recipe);
        }
        catch (IOException ex) {
            log.error("Error occurred", ex);
            ex.printStackTrace();
        }
    }
}
