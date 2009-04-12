package net.bodz.geom.util.gd;

import java.lang.reflect.Field;

import net.bodz.swt.auto.AbstractVisualType;
import net.bodz.swt.auto.VisualType;
import net.bodz.swt.auto.VisualTypes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class PropertyEntry2f extends Composite {

    private Label      name;
    private Composite  valueHolder;

    private Object     data;
    private Class<?>   dataType;
    private Field      field;
    private String     fieldName;

    private VisualType vt;
    private Control    visual;

    public PropertyEntry2f(Composite parent, String name, Object dataInit) {
        super(parent, SWT.NONE);

        assert dataInit != null;
        this.data = dataInit;
        dataType = dataInit.getClass();

        createContents();

        this.name.setText(name);
    }

    public PropertyEntry2f(Composite parent, Object dataInit, Field field) {
        super(parent, SWT.NONE);

        assert dataInit != null;
        assert field != null;
        this.data = dataInit;
        this.field = field;
        dataType = field.getType();

        createContents();

        this.name.setText(field.getName());
    }

    public PropertyEntry2f(Composite parent, Object dataInit, String fieldName) {
        super(parent, SWT.NONE);

        assert dataInit != null;
        assert fieldName != null;
        this.data = dataInit;
        this.fieldName = fieldName;
        dataType = AbstractVisualType.getFieldType(dataInit.getClass(),
                fieldName);

        createContents();

        this.name.setText(fieldName);
    }

    protected void createContents() {
        setLayout(new FillLayout());

        name = new Label(this, SWT.NONE);
        name.setText("<propertyName>");

        valueHolder = new Composite(this, SWT.NONE);
        valueHolder.setLayout(new FillLayout());

        vt = VisualTypes.getVisualType(dataType);
        if (vt == null) {
            throw new RuntimeException(
                    "The data type doesn't support visual feature.");
        }

        visual = vt.createControl(valueHolder);

        render();
    }

    public void render() {
        if (field != null) {
            vt.render(data, field, visual);
        } else if (fieldName != null) {
            vt.render(data, fieldName, visual);
        } else {
            vt.render(data, visual);
        }
    }

    public Object capture() {
        if (field != null) {
            vt.capture(visual, data, field);
        } else if (fieldName != null) {
            vt.capture(visual, data, fieldName);
        } else {
            data = vt.capture(visual, data);
        }
        return data;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    protected void checkSubclass() {
    }

}
