package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.base.IdEntity;
import net.bodz.violet.art.ArtifactModel;

@Table(name = "fabstdproc")
@IdType(Integer.class)
public class FabStdProcess
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    ArtifactModel output;
    FabFn fn;
    int duration;

    boolean strict;
    FabStdTest test;

    SizedList<FabStdProcessInput> inputs = new SizedList<>();

    public FabStdProcess() {
    }

    public ArtifactModel getOutput() {
        return output;
    }

    public void setOutput(ArtifactModel output) {
        this.output = output;
    }

    public FabFn getFn() {
        return fn;
    }

    public void setFn(FabFn fn) {
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

    public FabStdTest getTest() {
        return test;
    }

    public void setTest(FabStdTest test) {
        this.test = test;
    }

    public SizedList<FabStdProcessInput> getInputs() {
        return inputs;
    }

    public void setInputs(SizedList<FabStdProcessInput> inputs) {
        this.inputs = inputs;
    }

}
