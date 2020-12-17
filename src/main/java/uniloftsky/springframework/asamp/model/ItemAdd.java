package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemAdd extends BaseEntity {

    @NotNull
    @ManyToOne
    private CounterAgent counterAgent;

    @NotNull
    private Long count;

    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToOne
    private ItemName itemName;

    @NotNull
    @ManyToOne
    private ItemType itemType;

    @NotBlank
    private LocalDate date;

}
