package net.bodz.bas.err;

public class UnexpectedEnumException
        extends UnexpectedException {

    private static final long serialVersionUID = 1L;

    public UnexpectedEnumException() {
    }

    public UnexpectedEnumException(Enum<?> enumConstant) {
        super(String.valueOf(enumConstant));
    }

}
