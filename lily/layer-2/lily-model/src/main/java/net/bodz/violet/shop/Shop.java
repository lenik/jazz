package net.bodz.violet.shop;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;
import net.bodz.lily.model.base.DefaultAccessMode;
import net.bodz.lily.security.IAccessMode;

/**
 * 店铺
 */
@DefaultAccessMode(IAccessMode.M_PUBLIC)
@IdType(Integer.class)
@Table(name = "shop")
public class Shop
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    String hydm;

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
