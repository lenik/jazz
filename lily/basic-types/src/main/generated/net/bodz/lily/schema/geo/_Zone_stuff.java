package net.bodz.lily.schema.geo;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _Zone_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "zone";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_DEPTH = "depth";
    public static final String FIELD_TEL_CODE = "telcode";
    public static final String FIELD_POST_CODE = "postcode";
    public static final String FIELD_DATA = "data";

    public static final int N_ID = 10;
    public static final int N_CODE = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_COUNTRY = 2;
    public static final int N_PARENT_ID = 10;
    public static final int N_DEPTH = 10;
    public static final int N_TEL_CODE = 10;
    public static final int N_POST_CODE = 10;
    public static final int N_DATA = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 1;
    private static final int _ord_CATEGORY_ID = 15;
    private static final int _ord_COUNTRY = _ord_CATEGORY_ID + 1;
    private static final int _ord_PARENT_ID = _ord_COUNTRY + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;
    private static final int _ord_TEL_CODE = _ord_DEPTH + 1;
    private static final int _ord_POST_CODE = _ord_TEL_CODE + 1;
    private static final int _ord_DATA = _ord_POST_CODE + 2;

    @Id
    @NotNull
    int id;

    String code;

    String country;

    @NotNull
    int depth;

    String telCode;

    String postCode;

    Object data;

    /**  */
    Zone parent;

    Integer parentId;

    /**  */
    @NotNull
    ZoneCategory category;

    @NotNull
    int categoryId;

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

    @Ordinal(_ord_COUNTRY)
    @Precision(value = N_COUNTRY)
    @TextInput(maxLength = N_COUNTRY)
    @Column(name = "country", length = N_COUNTRY)
    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    @Ordinal(_ord_DEPTH)
    @Precision(value = 10)
    @Column(name = "depth", nullable = false, precision = 10)
    public int getDepth() {
        return depth;
    }

    public void setDepth(int value) {
        this.depth = value;
    }

    @Ordinal(_ord_TEL_CODE)
    @Precision(value = N_TEL_CODE)
    @TextInput(maxLength = N_TEL_CODE)
    @Column(name = "telcode", length = N_TEL_CODE)
    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String value) {
        this.telCode = value;
    }

    @Ordinal(_ord_POST_CODE)
    @Precision(value = N_POST_CODE)
    @TextInput(maxLength = N_POST_CODE)
    @Column(name = "postcode", length = N_POST_CODE)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String value) {
        this.postCode = value;
    }

    @Ordinal(_ord_DATA)
    @Precision(value = 2147483647)
    @Column(name = "data", precision = 2147483647)
    public Object getData() {
        return data;
    }

    public void setData(Object value) {
        this.data = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references lily.zone (id)
     */
    public Zone getParent() {
        return parent;
    }

    /**
     */
    public void setParent(Zone value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 10)
    public synchronized Integer getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Integer value) {
        this.parentId = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references lily.zonecat (id)
     */
    @NotNull
    public ZoneCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull ZoneCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    public void initNotNulls() {
    }

}
