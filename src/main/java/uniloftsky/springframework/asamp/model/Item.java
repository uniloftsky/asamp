package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @ManyToOne
    private ItemName itemName;

    @ManyToOne
    private ItemType itemType;

    private Long count;
    private BigDecimal price;
    private String img;

}
