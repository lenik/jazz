package net.bodz.violet.schema.art;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.BackrefRecord;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.inet.ExternalSite;

@IdType(Long.class)
public abstract class _ArtifactBackref_stuff
        extends BackrefRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art_backref";

    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_SITE_ID = "site";
    public static final String FIELD_KEY = "key";
    public static final String FIELD_VALUE = "value";

    public static final int N_ARTIFACT_ID = 10;
    public static final int N_SITE_ID = 10;
    public static final int N_KEY = 30;
    public static final int N_VALUE = 10;

    private static final int _ord_ARTIFACT_ID = 15;
    private static final int _ord_SITE_ID = _ord_ARTIFACT_ID + 1;
    private static final int _ord_KEY = _ord_SITE_ID + 1;
    private static final int _ord_VALUE = _ord_KEY + 1;

    String key;

    @NotNull
    int value;

    /**  */
    @NotNull
    ExternalSite site;

    @NotNull
    int siteId;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_KEY)
    @Precision(value = N_KEY)
    @TextInput(maxLength = N_KEY)
    @Column(name = "key", length = N_KEY)
    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = 10)
    @Column(name = "value", nullable = false, precision = 10)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @constraint foreign key (site) references lily.extsite (id)
     */
    @JoinColumn(name = "site")
    @ManyToOne
    @NotNull
    public ExternalSite getSite() {
        return site;
    }

    /**
     */
    public void setSite(@NotNull ExternalSite value) {
        this.site = value;
    }

    @Ordinal(_ord_SITE_ID)
    @Precision(value = 10)
    @Column(name = "site", nullable = false, precision = 10)
    public synchronized int getSiteId() {
        if (site != null) {
            if (site.getId() == null)
                return 0;
            return site.getId();
        }
        return siteId;
    }

    public synchronized void setSiteId(int value) {
        this.siteId = value;
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
    }

}
