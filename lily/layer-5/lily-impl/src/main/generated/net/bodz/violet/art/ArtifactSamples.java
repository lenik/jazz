package net.bodz.violet.art;

import java.math.BigDecimal;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArtifactSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact proto;
    public ArtifactPhase phase;
    public UOM uom;
    public User ownerUser;
    public ArtifactCategory category;

    public Artifact build()
            throws Exception {
        Artifact a = new Artifact();
        a.setOwnerGroup(ownerGroup);
        a.setProto(proto);
        a.setPhase(phase);
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setId(1365129348);
        a.setSkuCode("immoaubm.");
        a.setBarCode("v ratuvd rfii, a");
        a.setRfidCode("raee mlluwgi.");
        a.setModelName("Izs eunoub Ve fto nue#auoos_osae");
        a.setFinish((short)368);
        a.setPrice(new BigDecimal("713"));
        return a;
    }

}
