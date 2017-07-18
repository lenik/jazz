package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.cache.Statistics;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

@IdType(Integer.class)
@Table(name = "plancat")
public class PlanCategory
        extends CoNode<PlanCategory, Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_SKU_CODE_FORMAT = 100;
    public static final int N_BAR_CODE_FORMAT = 100;

    private String skuCodeFormat;
    private String barCodeFormat;
    private int count;

    /**
     * @label SKU代码格式
     */
    @OfGroup(StdGroup.Preferences.class)
    @TextInput(maxLength = N_SKU_CODE_FORMAT)
    public String getSkuCodeFormat() {
        return skuCodeFormat;
    }

    public void setSkuCodeFormat(String skuCodeFormat) {
        this.skuCodeFormat = skuCodeFormat;
    }

    /**
     * @label 条形码格式
     */
    @OfGroup(StdGroup.Preferences.class)
    @TextInput(maxLength = N_BAR_CODE_FORMAT)
    public String getBarCodeFormat() {
        return barCodeFormat;
    }

    public void setBarCodeFormat(String barCodeFormat) {
        this.barCodeFormat = barCodeFormat;
    }

    /**
     * 数目
     */
    @OfGroup(StdGroup.Statistics.class)
    @Statistics
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        skuCodeFormat = map.getString("skuCodeFormat", skuCodeFormat);
        barCodeFormat = map.getString("barCodeFormat", skuCodeFormat);
    }

}
