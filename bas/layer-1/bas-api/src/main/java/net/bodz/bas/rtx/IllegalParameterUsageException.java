package net.bodz.bas.rtx;

import net.bodz.bas.err.IllegalUsageException;

public class IllegalParameterUsageException
        extends IllegalUsageException {

    private static final long serialVersionUID = 1L;

    private final IOption parameter;

    public IllegalParameterUsageException(IOption parameter) {
        super("parameter " + parameter.getId());
        this.parameter = parameter;
    }

    public IllegalParameterUsageException(IOption parameter, String message, Throwable cause) {
        super("parameter " + parameter.getId() + ": " + message, cause);
        this.parameter = parameter;
    }

    public IllegalParameterUsageException(IOption parameter, String message) {
        super("parameter " + parameter.getId() + ": " + message);
        this.parameter = parameter;
    }

    public IllegalParameterUsageException(IOption parameter, Throwable cause) {
        super("parameter " + parameter.getId(), cause);
        this.parameter = parameter;
    }

    public IOption getParameter() {
        return parameter;
    }

}
