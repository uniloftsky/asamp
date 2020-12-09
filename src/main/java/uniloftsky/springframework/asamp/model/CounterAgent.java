package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class CounterAgent extends BaseEntity {

    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;

}
