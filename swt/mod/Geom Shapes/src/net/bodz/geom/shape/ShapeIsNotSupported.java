package net.bodz.geom.shape;

public class ShapeIsNotSupported extends RuntimeException {

    static final long serialVersionUID = 1547542465843905015L;

    public ShapeIsNotSupported() {
        super();
    }

    public ShapeIsNotSupported(String operationName, String className) {
        super("Shape of " + className + " is not supported by" + operationName
                + ". ");
    }

    public ShapeIsNotSupported(String operationName, Class<?> shapeClass) {
        this(operationName, shapeClass == null ? Object.class : shapeClass
                .getName());
    }

    public ShapeIsNotSupported(String operationName, Object shape) {
        this(operationName, shape == null ? "null" : shape.getClass().getName());
    }

    public ShapeIsNotSupported(String message, Throwable cause) {
        super(message, cause);
    }

    public ShapeIsNotSupported(String message) {
        super(message);
    }

    public ShapeIsNotSupported(Throwable cause) {
        super(cause);
    }

}
