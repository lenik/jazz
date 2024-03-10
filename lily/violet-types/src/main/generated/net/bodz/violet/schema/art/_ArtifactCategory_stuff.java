package net.bodz.violet.schema.art;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoCategory;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _ArtifactCategory_stuff<this_t extends _ArtifactCategory_stuff<this_t>>
        extends CoCategory<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "artcat";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_SKUFMT = "skufmt";
    public static final String FIELD_SKUFMTFULL = "skufmtfull";
    public static final String FIELD_BARFMT = "barfmt";
    public static final String FIELD_BARFMTFULL = "barfmtfull";
    public static final String FIELD_BATCHFMT = "batchfmt";
    public static final String FIELD_SERIALFMT = "serialfmt";
    public static final String FIELD_COUNT = "count";

    public static final int N_CODE = 20;
    public static final int N_SKUFMT = 100;
    public static final int N_SKUFMTFULL = 100;
    public static final int N_BARFMT = 100;
    public static final int N_BARFMTFULL = 100;
    public static final int N_BATCHFMT = 100;
    public static final int N_SERIALFMT = 100;
    public static final int N_COUNT = 10;

    private static final int _ord_CODE = 2;
    private static final int _ord_SKUFMT = 19;
    private static final int _ord_SKUFMTFULL = _ord_SKUFMT + 1;
    private static final int _ord_BARFMT = _ord_SKUFMTFULL + 1;
    private static final int _ord_BARFMTFULL = _ord_BARFMT + 1;
    private static final int _ord_BATCHFMT = _ord_BARFMTFULL + 1;
    private static final int _ord_SERIALFMT = _ord_BATCHFMT + 1;
    private static final int _ord_COUNT = _ord_SERIALFMT + 1;

    String code;

    String skufmt;

    String skufmtfull;

    String barfmt;

    String barfmtfull;

    String batchfmt;

    String serialfmt;

    @NotNull
    int count;

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

    @Ordinal(_ord_SKUFMT)
    @Precision(value = N_SKUFMT)
    @TextInput(maxLength = N_SKUFMT)
    @Column(name = "skufmt", length = N_SKUFMT)
    public String getSkufmt() {
        return skufmt;
    }

    public void setSkufmt(String value) {
        this.skufmt = value;
    }

    @Ordinal(_ord_SKUFMTFULL)
    @Precision(value = N_SKUFMTFULL)
    @TextInput(maxLength = N_SKUFMTFULL)
    @Column(name = "skufmtfull", length = N_SKUFMTFULL)
    public String getSkufmtfull() {
        return skufmtfull;
    }

    public void setSkufmtfull(String value) {
        this.skufmtfull = value;
    }

    @Ordinal(_ord_BARFMT)
    @Precision(value = N_BARFMT)
    @TextInput(maxLength = N_BARFMT)
    @Column(name = "barfmt", length = N_BARFMT)
    public String getBarfmt() {
        return barfmt;
    }

    public void setBarfmt(String value) {
        this.barfmt = value;
    }

    @Ordinal(_ord_BARFMTFULL)
    @Precision(value = N_BARFMTFULL)
    @TextInput(maxLength = N_BARFMTFULL)
    @Column(name = "barfmtfull", length = N_BARFMTFULL)
    public String getBarfmtfull() {
        return barfmtfull;
    }

    public void setBarfmtfull(String value) {
        this.barfmtfull = value;
    }

    @Ordinal(_ord_BATCHFMT)
    @Precision(value = N_BATCHFMT)
    @TextInput(maxLength = N_BATCHFMT)
    @Column(name = "batchfmt", length = N_BATCHFMT)
    public String getBatchfmt() {
        return batchfmt;
    }

    public void setBatchfmt(String value) {
        this.batchfmt = value;
    }

    @Ordinal(_ord_SERIALFMT)
    @Precision(value = N_SERIALFMT)
    @TextInput(maxLength = N_SERIALFMT)
    @Column(name = "serialfmt", length = N_SERIALFMT)
    public String getSerialfmt() {
        return serialfmt;
    }

    public void setSerialfmt(String value) {
        this.serialfmt = value;
    }

    @Ordinal(_ord_COUNT)
    @Precision(value = 10)
    @Column(name = "count", nullable = false, precision = 10)
    public int getCount() {
        return count;
    }

    public void setCount(int value) {
        this.count = value;
    }

    public void initNotNulls() {
    }

}
