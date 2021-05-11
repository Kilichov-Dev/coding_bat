package ecma.ai.codingbatapp.payload;

import ecma.ai.codingbatapp.entity.Category;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "Nom bo'sh bo'lmasligi kerak!")
    private String name;
    @NotNull(message = "Comment bo'sh bo'lmasligi kerak!")
    private String description;
    @NotNull(message = "Bajarilganlik true yoki false bo'lishi kerak!")
    private boolean completed;
    @NotNull(message = "Category bo'sh bo'lmasligi kerak!")
    private Integer categoryId;
}
