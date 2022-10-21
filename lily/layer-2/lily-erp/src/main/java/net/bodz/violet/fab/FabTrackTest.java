package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.IdEntity;

@Table(name = "fabtrack_test")
@IdType(Long.class)
public class FabTrackTest
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    FabTrack track;
    FabStdTest test;
    boolean valid;

    public FabTrackTest() {
    }

    public FabTrack getTrack() {
        return track;
    }

    public void setTrack(FabTrack track) {
        this.track = track;
    }

    public FabStdTest getTest() {
        return test;
    }

    public void setTest(FabStdTest test) {
        this.test = test;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
