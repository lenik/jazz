package net.bodz.violet.fab;

import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.fab.dao.FabProcessMapper;

public class FabProcessSerialSamples
        extends TestSampleBuilder {

    public FabProcess process;

    @Override
    public FabProcessSerial build()
            throws Exception {
        FabProcessSerial a = new FabProcessSerial();
        a.setProcess(process);
        a.setId(8107230821369900908L);
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
