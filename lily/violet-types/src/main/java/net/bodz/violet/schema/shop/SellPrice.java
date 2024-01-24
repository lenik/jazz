package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Table;


/**
 * 物品销售价格
 */
@Table(schema = "violet", name = "art_price")
public class SellPrice
        extends _SellPrice_stuff {

    private static final long serialVersionUID = 1L;


    public void setPrice(double price) {
        BigDecimal d = new BigDecimal(price);
        setPrice(d);
    }
}
