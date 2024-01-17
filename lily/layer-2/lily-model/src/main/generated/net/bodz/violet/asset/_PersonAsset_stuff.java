package net.bodz.violet.asset;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.art.Artifact;

@IdType(Long.class)
public abstract class _PersonAsset_stuff
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "asset_person";

    public static final String FIELD_OWNER_ID = "owner";
    public static final String FIELD_ARTIFACT_ID = "art";
    public static final String FIELD_BATCH = "batch";

    public static final int N_OWNER_ID = 10;
    public static final int N_ARTIFACT_ID = 10;
    public static final int N_BATCH = 2147483647;

    private static final int _ord_OWNER_ID = 15;
    private static final int _ord_ARTIFACT_ID = _ord_OWNER_ID + 1;
    private static final int _ord_BATCH = _ord_ARTIFACT_ID + 2;

    Object batch;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    /**  */
    @NotNull
    Person owner;

    @NotNull
    int ownerId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
    }

    /**
     *
     * @label art
     * @constraint foreign key (art) references violet.art (id)
     */
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
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(int value) {
        this.artifactId = value;
    }

    /**
     *
     * @label owner
     * @constraint foreign key (owner) references lily.person (id)
     */
    @NotNull
    public Person getOwner() {
        return owner;
    }

    /**
     */
    public void setOwner(@NotNull Person value) {
        this.owner = value;
    }

    @Ordinal(_ord_OWNER_ID)
    @Precision(value = 10)
    @Column(name = "owner", nullable = false, precision = 10)
    public synchronized int getOwnerId() {
        if (owner != null) {
            if (owner.getId() == null)
                return 0;
            return owner.getId();
        }
        return ownerId;
    }

    public synchronized void setOwnerId(int value) {
        this.ownerId = value;
    }

    public void initNotNulls() {
    }

}
