package net.bodz.violet.schema.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.fab.dao.FabProcessMapper;

public class FabProcessSerialSamples
        extends TestSampleBuilder {

    public FabProcess process;

    @Override
    public FabProcessSerial build()
            throws Exception {
        FabProcessSerial a = new FabProcessSerial();
        a.setProcess(process);
        a.setSerial("kn_er orii, qe*gcazouuzus.");
        return a;
    }

    @Override
    public FabProcessSerialSamples wireAny(IRandomPicker picker) {
        this.process = picker.pickAny(FabProcessMapper.class, "fabproc");
        return this;
    }

    @Override
    public FabProcessSerial buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
