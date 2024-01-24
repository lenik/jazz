package net.bodz.violet.schema.shop;

import java.util.List;

import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public class Cart
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    List<CartItem> items;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

}
