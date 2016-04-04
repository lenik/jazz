package net.bodz.lily.model.mixin;

import java.io.Serializable;

import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.model.base.schema.FormDef;

public class UseForm
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private FormDef def;
    private String args;

    /**
     * <p lang="zh">
     * 正文的形式。
     * 
     * @label Form Def
     * @label.zh 形式
     */
    @OfGroup(StdGroup.Metadata.class)
    public FormDef getDef() {
        return def;
    }

    public void setDef(FormDef def) {
        this.def = def;
    }

    public int getDefId(int fallback) {
        if (def == null)
            return fallback;
        Integer id = def.getId();
        return id == null ? fallback : id;
    }

    public Integer getDefId() {
        if (def == null)
            return null;
        else
            return def.getId();
    }

    /**
     * <p lang="zh">
     * 形式的可选参数。
     * 
     * @label Form Arguments
     * @label.zh 形式参数
     * @placeholder 输入形式参数
     */
    @OfGroup(StdGroup.Metadata.class)
    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

}
