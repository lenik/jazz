package net.bodz.violet.art;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.art.dao.ArtifactCategoryMapper;

public class ArtifactCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public ArtifactCategory parent;

    @Override
    public ArtifactCategory build()
            throws Exception {
        ArtifactCategory a = new ArtifactCategory();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setCode("cb h_paas! vh. lukh");
        a.setSkufmt("Lbxa-raio, ntqaps seu quls aeua ueau hkw#jobgl zbbaei gvwuepx oe, booe ul ah; urru, Cucvig");
        a.setSkufmtfull("aumd");
        a.setBarfmt("a uu; y*Cfzg. oecidu#umsvo#niuc, ipe o K xoe woeo at si? ivte, nxmahu vuosi, eau fqpm Exishdq.");
        a.setBarfmtfull("H; gqry iodev! oiu; aivw! uoi. Uaeie oi mjzy; eyur! vt ivmedo e txy lr");
        a.setBatchfmt("ir a e fhoie, z an eoypqe'caaiqo. euz woeul cc? jrm!");
        a.setSerialfmt("Lea&Rlua aakui cuuic, Vjya; wdsdus@nwaiau eosjh");
        a.setCount(753616462);
        return a;
    }

    @Override
    public ArtifactCategorySamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(ArtifactCategoryMapper.class, "artcat");
        return this;
    }

    @Override
    public ArtifactCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
