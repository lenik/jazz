package net.bodz.lily.schema.vapp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _VApp_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vapp";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_REQ_ID = "req";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_SECRET = "secret";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_REQ_ID = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_SECRET = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = 14;
    private static final int _ord_REQ_ID = _ord_CODE + 2;
    private static final int _ord_CATEGORY_ID = _ord_REQ_ID + 1;
    private static final int _ord_SECRET = _ord_CATEGORY_ID + 1;

    @Id
    @NotNull
    int id;

    String code;

    @NotNull
    String secret;

    /**  */
    VAppCat category;

    Integer categoryId;

    /**  */
    VAppRequest req;

    Integer reqId;

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
     * @label cat
     * @constraint foreign key (cat) references lily.vappcat (id)
     */
    public VAppCat getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(VAppCat value) {
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
     * @label req
     * @constraint foreign key (req) references lily.vappreq (id)
     */
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
