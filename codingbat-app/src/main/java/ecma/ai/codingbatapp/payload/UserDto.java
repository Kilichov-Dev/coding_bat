package ecma.ai.codingbatapp.payload;

import ecma.ai.codingbatapp.entity.StarBadge;
import ecma.ai.codingbatapp.entity.Task;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
public class UserDto {

    @NotNull(message = "bo'sh bo'lmasin")
    @NotEmpty(message = "Empty bo'lmasin")
    private String email;

    @NotNull(message = "bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "bo'sh bo'lmasin")
    private String fullName;

    @OneToMany
    private List<Integer> taskList;

    @ManyToOne
    private Integer starBadgeId;

}
