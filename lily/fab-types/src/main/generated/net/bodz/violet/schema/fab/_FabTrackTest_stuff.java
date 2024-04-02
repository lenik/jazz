package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _FabTrackTest_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack_test";

    public static final String FIELD_TRACK_ID = "track";
    public static final String FIELD_STANDARD_ID = "std";
    public static final String FIELD_VALID = "valid";

    public static final int N_TRACK_ID = 19;
    public static final int N_STANDARD_ID = 10;
    public static final int N_VALID = 1;

    private static final int _ord_TRACK_ID = 5;
    private static final int _ord_STANDARD_ID = _ord_TRACK_ID + 1;
    private static final int _ord_VALID = _ord_STANDARD_ID + 1;

    @NotNull
    boolean valid;

    /**  */
    @NotNull
    FabTrack track;

    @NotNull
    long trackId;

    /**  */
    @NotNull
    FabStdTest standard;

    @NotNull
    int standardId;

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    /**
     *
     * @constraint foreign key (track) references violet.fabtrack (id)
     */
    @JoinColumn(name = "track")
    @ManyToOne
    @NotNull
    public FabTrack getTrack() {
        return track;
    }

    /**
     */
    public void setTrack(@NotNull FabTrack value) {
        this.track = value;
    }

    @Ordinal(_ord_TRACK_ID)
    @Precision(value = 19)
    @Column(name = "track", nullable = false, precision = 19)
    public synchronized long getTrackId() {
        if (track != null) {
            if (track.getId() == null)
                return 0L;
            return track.getId();
        }
        return trackId;
    }

    public synchronized void setTrackId(long value) {
        this.trackId = value;
    }

    /**
     *
     * @constraint foreign key (std) references violet.fabstdtest (id)
     */
    @JoinColumn(name = "std")
    @ManyToOne
    @NotNull
    public FabStdTest getStandard() {
        return standard;
    }

    /**
     */
    public void setStandard(@NotNull FabStdTest value) {
        this.standard = value;
    }

    @Ordinal(_ord_STANDARD_ID)
    @Precision(value = 10)
    @Column(name = "std", nullable = false, precision = 10)
    public synchronized int getStandardId() {
        if (standard != null) {
            if (standard.getId() == null)
                return 0;
            return standard.getId();
        }
        return standardId;
    }

    public synchronized void setStandardId(int value) {
        this.standardId = value;
    }

    public void initNotNulls() {
    }

}
