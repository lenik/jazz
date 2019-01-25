package net.bodz.violet.art;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.repr.EntGroup;
import net.bodz.lily.template.RichProperties;

public class ArtifactProperties
        extends RichProperties {

    private static final long serialVersionUID = 1L;

    public static final int N_COLOR = 20;
    public static final int N_CAUTION = 100;

    static final String K_CAUTION = "caution";
    static final String K_COLOR = "color";
    // static final String K_FILES = "files";
    static final String K_SUPPLY_DELAY = "supply.delay";

    public ArtifactProperties() {
        super();
    }

    /**
     * 警告提示
     *
     * 如危险品、易碎、易爆炸等。
     *
     * @placeholder 输入警告信息…
     */
    @Priority(100)
    @TextInput(maxLength = N_CAUTION)
    public String getCaution() {
        return getAttribute(K_CAUTION);
    }

    public void setCaution(String caution) {
        setAttribute(K_CAUTION, caution);
    }

    /**
     * 颜色
     *
     * 描述物料的外观颜色。
     *
     * 对于系列商品不同的颜色型号，应该分别建立不同的物料。
     *
     * @placeholder 输入颜色名称…
     */
    @OfGroup(EntGroup.ColorManagement.class)
    @TextInput(maxLength = N_COLOR)
    public String getColor() {
        return getAttribute(K_COLOR);
    }

    public void setColor(String color) {
        setAttribute(K_COLOR, color);
    }

    /**
     * 供应延时 (天)
     *
     * 指开始订购到收到货品实物的最大时间间隔，用于指导采购、生产计划。
     */
    @OfGroup(StdGroup.Schedule.class)
    public int getSupplyDelay() {
        return getAttribute(K_SUPPLY_DELAY, 0);
    }

    public void setSupplyDelay(int supplyDelay) {
        setAttribute(K_SUPPLY_DELAY, supplyDelay);
    }

    @Override
    protected boolean readFromJson(String key, Object val)
            throws ParseException {
        return super.readFromJson(key, val);
    }

}
