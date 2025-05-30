package net.bodz.lily.concrete.util;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.schema.meta.FormDef;

public class UseForm
        extends MixinStruct {

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
        Integer id = def.id();
        return id == null ? fallback : id;
    }

    public Integer getDefId() {
        if (def == null)
            return null;
        else
            return def.id();
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

    @Override
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        def = o.readInto("def", def, () -> new FormDef());
        args = o.getString("args", args);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        out.entryNotNull("def", def);
        out.entry("args", args);
    }

}
