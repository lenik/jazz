package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _FormParameter_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_NAME = 30;
    public static final int N_VALUE = 100;

    private static final int _ord_ID = 1;
    private static final int _ord_FORM_ID = _ord_ID + 1;
    private static final int _ord_NAME = _ord_FORM_ID + 1;
    private static final int _ord_VALUE = _ord_NAME + 1;

    @Id
    @NotNull
    int id;

    String name;

    String value;

    /**  */
    @NotNull
    FormDef form;

    @NotNull
    int formId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_NAME)
    @Precision(value = N_NAME)
    @TextInput(maxLength = N_NAME)
    @Column(name = "name", length = N_NAME)
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = N_VALUE)
    @TextInput(maxLength = N_VALUE)
    @Column(name = "value", length = N_VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @label form
     * @constraint foreign key (form) references lily._form (id)
     */
    @NotNull
    public FormDef getForm() {
        return form;
    }

    /**
     */
    public void setForm(@NotNull FormDef value) {
        this.form = value;
    }

    @Ordinal(_ord_FORM_ID)
    @Precision(value = 10)
    @Column(name = "form", nullable = false, precision = 10)
    public synchronized int getFormId() {
        if (form != null) {
            if (form.getId() == null)
                return 0;
            return form.getId();
        }
        return formId;
    }

    public synchronized void setFormId(int value) {
        this.formId = value;
    }

    public void initNotNulls() {
    }

}
