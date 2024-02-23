package net.bodz.lily.schema.contact;

import javax.persistence.Column;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Organization_stuff
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "org";

    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_ROLE_COUNT = "nrole";
    public static final String FIELD_BANK_COUNT = "nbank";
    public static final String FIELD_SIZE = "size";
    public static final String FIELD_TAX_ID = "taxid";

    public static final int N_PROPERTIES = 2147483647;
    public static final int N_ROLE_COUNT = 10;
    public static final int N_BANK_COUNT = 10;
    public static final int N_SIZE = 10;
    public static final int N_TAX_ID = 20;

    private static final int _ord_PROPERTIES = 24;
    private static final int _ord_ROLE_COUNT = _ord_PROPERTIES + 5;
    private static final int _ord_BANK_COUNT = _ord_ROLE_COUNT + 4;
    private static final int _ord_SIZE = _ord_BANK_COUNT + 1;
    private static final int _ord_TAX_ID = _ord_SIZE + 1;

    JsonVariant properties;

    @NotNull
    int roleCount;

    @NotNull
    int bankCount;

    @NotNull
    int size;

    String taxId;

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    @Ordinal(_ord_ROLE_COUNT)
    @Precision(value = 10)
    @Column(name = "nrole", nullable = false, precision = 10)
    public int getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(int value) {
        this.roleCount = value;
    }

    @Ordinal(_ord_BANK_COUNT)
    @Precision(value = 10)
    @Column(name = "nbank", nullable = false, precision = 10)
    public int getBankCount() {
        return bankCount;
    }

    public void setBankCount(int value) {
        this.bankCount = value;
    }

    @Ordinal(_ord_SIZE)
    @Precision(value = 10)
    @Column(name = "size", nullable = false, precision = 10)
    public int getSize() {
        return size;
    }

    public void setSize(int value) {
        this.size = value;
    }

    @Ordinal(_ord_TAX_ID)
    @Precision(value = N_TAX_ID)
    @TextInput(maxLength = N_TAX_ID)
    @Column(name = "taxid", length = N_TAX_ID)
    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String value) {
        this.taxId = value;
    }

    public void initNotNulls() {
        this.getContact().setAddress1("");
        this.getContact().setAddress2("");
    }

}
