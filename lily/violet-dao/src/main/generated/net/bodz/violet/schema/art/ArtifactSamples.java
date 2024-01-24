package net.bodz.violet.schema.art;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactCategoryMapper;
import net.bodz.violet.schema.art.dao.ArtifactMapper;
import net.bodz.violet.schema.art.dao.ArtifactPhaseMapper;
import net.bodz.violet.schema.art.dao.UOMMapper;

public class ArtifactSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact proto;
    public ArtifactPhase phase;
    public UOM uom;
    public User ownerUser;
    public ArtifactCategory category;

    @Override
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

    @Override
    public ArtifactSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.proto = picker.pickAny(ArtifactMapper.class, "art");
        this.phase = picker.pickAny(ArtifactPhaseMapper.class, "artphase");
        this.uom = picker.pickAny(UOMMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(ArtifactCategoryMapper.class, "artcat");
        return this;
    }

    @Override
    public Artifact buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
