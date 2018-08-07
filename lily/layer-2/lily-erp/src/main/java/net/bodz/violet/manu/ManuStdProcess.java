package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.ArtifactModel;

@Table(name = "manustdproc")
@IdType(Integer.class)
public class ManuStdProcess
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ArtifactModel output;
    ManuFn fn;
    int duration;

    boolean strict;
    ManuStdTest test;

    SizedList<ManuStdProcessInput> inputs = new SizedList<>();

    public ManuStdProcess() {
    }

    public ArtifactModel getOutput() {
        return output;
    }

    public void setOutput(ArtifactModel output) {
        this.output = output;
    }

    public ManuFn getFn() {
        return fn;
    }

    public void setFn(ManuFn fn) {
        this.fn = fn;
    }

    /**
     * expected consume time, in seconds.
     *
     * avg. wage in minute: cpst.labor / duration * 60
     */
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * strict test, testing is required.
     */
    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public ManuStdTest getTest() {
        return test;
    }

    public void setTest(ManuStdTest test) {
        this.test = test;
    }

    public SizedList<ManuStdProcessInput> getInputs() {
        return inputs;
    }

    public void setInputs(SizedList<ManuStdProcessInput> inputs) {
        this.inputs = inputs;
    }

}
