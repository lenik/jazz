package net.bodz.violet.schema.art;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.DocRecord;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _ArtifactDoc_stuff
        extends DocRecord {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "art_doc";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_ARTIFACT_ID = "art";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_ARTIFACT_ID = 10;

    private static final int _ord_FORM_ARGUMENTS = 19;
    private static final int _ord_ARTIFACT_ID = _ord_FORM_ARGUMENTS + 1;

    String formArguments;

    /**  */
    @NotNull
    Artifact artifact;

    @NotNull
    int artifactId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
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
