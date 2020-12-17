package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class ItemName extends BaseEntity {

    @NotBlank
    private String itemName;

}
