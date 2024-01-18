package net.bodz.violet.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _FabTrackTest_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack_test";

    public static final String FIELD_ID = "id";
    public static final String FIELD_TRACK_ID = "track";
    public static final String FIELD_STANDARD_ID = "std";
    public static final String FIELD_VALID = "valid";

    public static final int N_ID = 19;
    public static final int N_TRACK_ID = 19;
    public static final int N_STANDARD_ID = 10;
    public static final int N_VALID = 1;

    private static final int _ord_ID = 1;
    private static final int _ord_TRACK_ID = _ord_ID + 4;
    private static final int _ord_STANDARD_ID = _ord_TRACK_ID + 1;
    private static final int _ord_VALID = _ord_STANDARD_ID + 1;

    @Id
    @NotNull
    long id;

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

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

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
     * @label track
     * @constraint foreign key (track) references violet.fabtrack (id)
     */
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
            return track.getId();
        }
        return trackId;
    }

    public synchronized void setTrackId(long value) {
        this.trackId = value;
    }

    /**
     *
     * @label std
     * @constraint foreign key (std) references violet.fabstdtest (id)
     */
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
