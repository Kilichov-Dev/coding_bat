package ecma.ai.codingbatapp.payload;

import ecma.ai.codingbatapp.entity.ProgLang;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {
    @NotNull(message = "The name should not be empty!!!")
    private String name;

    @NotNull(message = "Description should not be empty!!!")
    private String description;

    @NotNull(message = "starNumber should not be empty!!!")
    private int starNumber;

    @NotNull(message = "Language should not be empty!!!")
    private List<Integer> languageId;
}
