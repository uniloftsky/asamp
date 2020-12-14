package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @ManyToOne
    private ItemName itemName;

    @ManyToOne
    private ItemType itemType;

    private Long count;
    private Integer inDiameter;
    private Integer outDiameter;
    private String img;

}
