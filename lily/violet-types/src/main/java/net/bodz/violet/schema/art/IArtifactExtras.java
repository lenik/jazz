package net.bodz.violet.schema.art;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.lily.repr.EntGroup;

public interface IArtifactExtras {

    int N_COLOR = 20;
    int N_CAUTION = 100;

    /**
     * 警告提示
     *
     * 如危险品、易碎、易爆炸等。
     *
     * @placeholder 输入警告信息…
     */
    @Priority(100)
    @TextInput(maxLength = N_CAUTION)
    String getCaution();

    void setCaution(String caution);

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
    String getColor();

    void setColor(String color);

    /**
     * 供应延时 (天)
     *
     * 指开始订购到收到货品实物的最大时间间隔，用于指导采购、生产计划。
     */
    @OfGroup(StdGroup.Schedule.class)
    int getSupplyDelay();

    void setSupplyDelay(int supplyDelay);

}
