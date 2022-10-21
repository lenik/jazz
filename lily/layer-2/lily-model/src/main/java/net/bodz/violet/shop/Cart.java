package net.bodz.violet.shop;

import java.util.List;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

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
