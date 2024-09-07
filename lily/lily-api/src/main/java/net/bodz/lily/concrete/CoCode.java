package net.bodz.lily.concrete;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.bas.site.viz.input.Tagsinput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.FieldGroupVue;

@FieldGroupVue
@Tagsinput(typer = CoCodeTagTyper.class)
@TsTyped
@TypeParameters({ TypeParamType.THIS_REC })
@IdType(Integer.class)
public abstract class CoCode<self_t extends CoCode<self_t>>
        extends CoNode<self_t, Integer>
        implements
            IUniqueNamed {

    private static final long serialVersionUID = 1L;

    public static final String FIELD_CODE = "code";

    public static final int N_CODE = 30;

    String code;

    public CoCode() {
        super();
    }

    public CoCode(self_t parent) {
        super(parent);
    }

    /**
     * Code
     *
     * @label Code
     * @label.zh 代码
     */
    @Column(name = FIELD_CODE, nullable = true, precision = N_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getUniqueName() {
        return getCode();
    }

    @Override
    public void setUniqueName(String name) {
        setCode(name);
    }

}
