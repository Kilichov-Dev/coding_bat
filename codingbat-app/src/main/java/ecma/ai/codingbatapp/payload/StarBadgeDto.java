package ecma.ai.codingbatapp.payload;

import ecma.ai.codingbatapp.entity.enums.StarBadgeValue;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StarBadgeDto {
    @NotNull(message = "Value bo'sh bo'lmasligi kerak!")
    private StarBadgeValue value;//5 10 25
    @NotNull(message = "Language bo'sh bo'lmasligi kerak!")
    private Integer languageId; //til ko'payishi mumkin
}
