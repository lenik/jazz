package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

/**
 * 店铺
 */
@IdType(Integer.class)
@Table(name = "shop")
public class Shop
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    String hydm;

    @Override
    public void reinit() {
        super.reinit();
        setAccessMode(M_PUBLIC);
    }

    /**
     * 行业代码
     * 
     * @placeholder 输入运单号…
     */
    public String getHydm() {
        return hydm;
    }

    public void setHydm(String hydm) {
        this.hydm = hydm;
    }

}
