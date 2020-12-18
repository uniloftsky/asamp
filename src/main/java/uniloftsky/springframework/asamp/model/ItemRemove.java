package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemRemove extends BaseEntity {

    @NotNull(message = "{itemAdd.counterAgent.NotNull}")
    @ManyToOne
    private CounterAgent counterAgent;

    @NotNull(message = "{itemAdd.count.NotNull}")
    @Min(value = 1, message = "{itemAdd.count.Min}")
    @Max(value = 999, message = "{itemAdd.count.Max}")
    private Long count;

    @NotNull(message = "{itemAdd.price.NotNull}")
    @Min(value = 1, message = "{itemAdd.price.Min}")
    private BigDecimal price;

    @NotNull(message = "{itemAdd.itemName.NotNull}")
    @ManyToOne
    private ItemName itemName;

    @NotNull(message = "{itemAdd.itemType.NotNull}")
    @ManyToOne
    private ItemType itemType;

    private LocalDate date;

}
