package net.bodz.lily.schema.vapp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _VApp_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vapp";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_FILES = "files";
    public static final String FIELD_REQ_ID = "req";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_SECRET = "secret";

    public static final int N_CODE = 30;
    public static final int N_PROPERTIES = 2147483647;
    public static final int N_FILES = 2147483647;
    public static final int N_REQ_ID = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_SECRET = 2147483647;

    private static final int _ord_CODE = 15;
    private static final int _ord_PROPERTIES = _ord_CODE + 1;
    private static final int _ord_FILES = _ord_PROPERTIES + 1;
    private static final int _ord_REQ_ID = _ord_FILES + 1;
    private static final int _ord_CATEGORY_ID = _ord_REQ_ID + 1;
    private static final int _ord_SECRET = _ord_CATEGORY_ID + 1;

    String code;

    JsonVariant properties;

    JsonVariant files;

    @NotNull
    String secret;

    /**  */
    VAppCategory category;

    Integer categoryId;

    /**  */
    VAppRequest req;

    Integer reqId;

    @Ordinal(_ord_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    @Column(name = "code", length = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
    }

    @Ordinal(_ord_FILES)
    @Precision(value = 2147483647)
    @Column(name = "files", precision = 2147483647)
    public JsonVariant getFiles() {
        return files;
    }

    public void setFiles(JsonVariant value) {
        this.files = value;
    }

    @Ordinal(_ord_SECRET)
    @NotNull
    @Precision(value = N_SECRET)
    @TextInput(maxLength = N_SECRET)
    @Column(name = "secret", nullable = false, length = N_SECRET)
    public String getSecret() {
        return secret;
    }

    public void setSecret(@NotNull String value) {
        this.secret = value;
    }

    /**
     *
     * @constraint foreign key (cat) references lily.vappcat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    public VAppCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(VAppCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (req) references lily.vappreq (id)
     */
    @JoinColumn(name = "req")
    @ManyToOne
    public VAppRequest getReq() {
        return req;
    }

    /**
     */
    public void setReq(VAppRequest value) {
        this.req = value;
    }

    @Ordinal(_ord_REQ_ID)
    @Precision(value = N_REQ_ID)
    @Column(name = "req", precision = 10)
    public synchronized Integer getReqId() {
        if (req != null) {
            return req.getId();
        }
        return reqId;
    }

    public synchronized void setReqId(Integer value) {
        this.reqId = value;
    }

    public void initNotNulls() {
        this.secret = "";
    }

}
