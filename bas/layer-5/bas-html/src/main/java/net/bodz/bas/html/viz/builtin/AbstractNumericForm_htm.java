package net.bodz.bas.html.viz.builtin;

public abstract class AbstractNumericForm_htm<T extends Number>
        extends AbstractTextForm_htm<T> {

    public AbstractNumericForm_htm(Class<T> valueClass) {
        super(valueClass);
    }

}
