package net.bodz.lily.model.base;

import net.bodz.bas.repr.form.meta.TextInput;

public abstract class CoCode<self_t extends CoCode<self_t>>
        extends CoNode<self_t> {

    private static final long serialVersionUID = 1L;

    private int id;

    public CoCode() {
        super();
    }

    public CoCode(self_t parent) {
        super(parent);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Code
     * 
     * @label Code
     * @label.zh.cn 代码
     */
    @TextInput(maxLength = N_CODE_NAME)
    public String getCode() {
        return getCodeName();
    }

    public void setCode(String code) {
        setCodeName(code);
    }

}
