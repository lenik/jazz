package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;

@IdType(Integer.class)
public abstract class _SellPrice_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art_price";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_PRICE = "price";

    public static final int N_CODE = 20;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_PRICE = 12;

    private static final int _ord_CODE = 2;
    private static final int _ord_ARTIFACT_ID = _ord_CODE + 8;
    private static final int _ord_PRICE = _ord_ARTIFACT_ID + 1;

    String code;

    @NotNull
    BigDecimal price;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

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

    @Ordinal(_ord_PRICE)
    @NotNull
    @Precision(value = N_PRICE, scale = 2)
    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull BigDecimal value) {
        this.price = value;
    }

    /**
     *
     * @constraint foreign key (art) references violet.art (id)
     */
    @JoinColumn(name = "art")
    @ManyToOne
    @NotNull
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(@NotNull Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = 10)
    @Column(name = "art", nullable = false, precision = 10)
    public synchronized int getArtifactId() {
        if (artifact != null) {
            if (artifact.getId() == null)
                return 0;
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
    }

    public void initNotNulls() {
        this.price = BigDecimal.ZERO;
    }

}
