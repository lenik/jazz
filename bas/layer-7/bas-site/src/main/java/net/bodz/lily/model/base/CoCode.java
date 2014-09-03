package net.bodz.lily.model.base;

public abstract class CoCode<self_t extends CoCode<self_t>>
        extends CoNode<self_t> {

    private static final long serialVersionUID = 1L;

    public CoCode() {
        super();
    }

    public CoCode(self_t parent) {
        super(parent);
    }

    public String getCode() {
        return getCodeName();
    }

    public void setCode(String code) {
        setCodeName(code);
    }

}
