package net.bodz.swt.ui.input;

import org.eclipse.swt.widgets.Control;

@SuppressWarnings("unchecked")
public abstract class AbstractFieldEntry<T, U extends Control, self_t extends AbstractFieldEntry<T, U, self_t>>
        implements
            IFieldEntry<T> {

    public Control label;
    public U input;

    public AbstractFieldEntry(Control label, U input) {
        this.label = label;
        this.input = input;
    }

    @Override
    public Control getLabelControl() {
        return label;
    }

    @Override
    public Control getInputControl() {
        return input;
    }

    public self_t value(T value) {
        setValue(value);
        return (self_t) this;
    }

}
