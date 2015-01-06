package net.bodz.lily.model.base;

import net.bodz.bas.repr.form.meta.TextInput;

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
    @TextInput(maxLength = N_CODE_NAME)
    public String getCode() {
        return getCodeName();
    }

    public void setCode(String code) {
        setCodeName(code);
    }

}
