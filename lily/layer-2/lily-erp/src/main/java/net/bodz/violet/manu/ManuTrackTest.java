package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "manutrack_test")
@IdType(Long.class)
public class ManuTrackTest
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    ManuTrack track;
    ManuStdTest test;
    boolean valid;

    public ManuTrackTest() {
    }

    public ManuTrack getTrack() {
        return track;
    }

    public void setTrack(ManuTrack track) {
        this.track = track;
    }

    public ManuStdTest getTest() {
        return test;
    }

    public void setTest(ManuStdTest test) {
        this.test = test;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
