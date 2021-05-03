package hello.itemservice.domain.item;

import lombok.Data;

@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price=0; //안들어갈수도 있어서 int대신
    private Integer quantity=0;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
