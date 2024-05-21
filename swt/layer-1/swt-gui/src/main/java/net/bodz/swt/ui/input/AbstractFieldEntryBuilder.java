package net.bodz.swt.ui.input;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import net.bodz.bas.t.ref.reactive.Ref;

@SuppressWarnings("unchecked")
public abstract class AbstractFieldEntryBuilder<T, U extends Control, F extends IFieldEntry<T>, self_t extends AbstractFieldEntryBuilder<T, U, F, self_t>> {

    FieldEntryForm context;
    String label;
    int verticalAlign;
    int style;

    T initialValue; // initial value

    Supplier<T> getter;
    Consumer<T> setter;
    Ref<T> ref;

    public AbstractFieldEntryBuilder(FieldEntryForm context) {
        this.context = context;
    }

    public self_t label(String label) {
        this.label = label;
        return (self_t) this;
    }

    public self_t alignTop() {
        verticalAlign = SWT.TOP;
        return (self_t) this;
    }

    public self_t alignCenter() {
        verticalAlign = SWT.CENTER;
        return (self_t) this;
    }

    public self_t alignBottom() {
        verticalAlign = SWT.BOTTOM;
        return (self_t) this;
    }

    public self_t initialValue(T value) {
        this.initialValue = value;
        return (self_t) this;
    }

    public self_t getter(Supplier<T> getter) {
        this.getter = getter;
        return (self_t) this;
    }

    public self_t setter(Consumer<T> setter) {
        this.setter = setter;
        return (self_t) this;
    }

    public self_t property(Supplier<T> getter, Consumer<T> setter) {
        this.getter = getter;
        this.setter = setter;
        return (self_t) this;
    }

    public self_t property(Ref<T> ref) {
        this.getter = ref::get;
        this.setter = ref::set;
        this.ref = ref;
        return (self_t) this;
    }

    public F build() {
        Label labelControl = new Label(context.parent, SWT.NONE);
        FormData formData = new FormData();
        formData.left = new FormAttachment(0);
        if (context.getLast() != null) {
            formData.top = new FormAttachment(context.getLast().getLabelControl(), context.vSkip, SWT.BOTTOM);
        } else {
            formData.top = new FormAttachment(0);
        }
        labelControl.setLayoutData(formData);
        labelControl.setText(this.label + ":");

        F formField = build2(labelControl);

        if (initialValue == null)
            if (getter != null)
                initialValue = getter.get();
        if (initialValue != null)
            formField.setValue(initialValue);

        if (ref != null)
            ref.addObserver((_ref, value) -> {
                formField.setValue(value);
            });

        context.entries.add(formField);
        return formField;
    }

    protected abstract F build2(Control labelControl);

}
