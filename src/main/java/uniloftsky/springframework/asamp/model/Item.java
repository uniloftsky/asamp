package uniloftsky.springframework.asamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Item extends BaseEntity {

    @NotNull
    @ManyToOne
    private ItemName itemName;

    @NotNull
    @ManyToOne
    private ItemType itemType;

    @NotNull(message = "{item.count.NotNull}")
    @Min(value = 1, message = "{item.count.Min}")
    @Max(value = 999, message = "{item.count.Max")
    private Long count;

    @NotNull(message = "{item.inDiameter.NotNull}")
    @Min(value = 1, message = "{item.inDiameter.Min}")
    @Max(value = 999, message = "{item.inDiameter.Max")
    private Integer inDiameter;

    @NotNull(message = "{item.outDiameter.NotNull}")
    @Min(value = 1, message = "{item.outDiameter.Min}")
    @Max(value = 999, message = "{item.outDiameter.Max")
    private Integer outDiameter;

    private String img;

}
