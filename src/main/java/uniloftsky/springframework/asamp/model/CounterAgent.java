package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class CounterAgent extends BaseEntity {

    @NotBlank(message = "{counterAgent.firstName.NotBlank}")
    @Size(min = 1, max = 50, message = "{counterAgent.firstName.Size}")
    private String firstName;

    @NotBlank(message = "{counterAgent.lastName.NotBlank}")
    @Size(min = 1, max = 50, message = "{counterAgent.lastName.Size}")
    private String lastName;

    @NotBlank(message = "{counterAgent.middleName.NotBlank}")
    @Size(min = 1, max = 50, message = "{counterAgent.middleName.Size}")
    private String middleName;

    @NotBlank(message = "{counterAgent.phone.NotBlank}")
    @Size(min = 1, max = 15, message = "{counterAgent.phone.Size}")
    private String phone;

    @NotBlank(message = "{counterAgent.email.NotBlank}")
    @Size(min = 1, max = 50, message = "{counterAgent.email.Size}")
    private String email;

}
