package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemRemove extends BaseEntity {

    @ManyToOne
    private CounterAgent counterAgent;

    private Long count;
    private BigDecimal price;

    @ManyToOne
    private ItemName itemName;

    private LocalDate date;

}
