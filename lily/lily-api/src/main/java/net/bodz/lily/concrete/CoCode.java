package net.bodz.lily.concrete;

import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.site.viz.input.Tagsinput;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@Tagsinput(typer = CoCodeTagTyper.class)
@TypeParameters({ TypeParamType.THIS_REC })
@IdType(Integer.class)
public abstract class CoCode<self_t extends CoCode<self_t>>
        extends CoNode<self_t, Integer> {

    private static final long serialVersionUID = 1L;

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
    @TextInput(maxLength = N_UNIQ_NAME)
    public String getCode() {
        // reuse the member var.
        return getUniqName();
    }

    public void setCode(String code) {
        // reuse the member var.
        setUniqName(code);
    }

}
