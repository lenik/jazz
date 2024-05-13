package net.bodz.violet.schema.issue;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.Uom;
import net.bodz.lily.schema.util.dao.UomMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class IssueParameterSamples
        extends TestSampleBuilder {

    public Uom uom;
    public User ownerUser;
    public Group ownerGroup;

    @Override
    public IssueParameter build()
            throws Exception {
        IssueParameter a = new IssueParameter();
        a.setUom(uom);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setName("eh duipz iow'q vux");
        return a;
    }

    @Override
    public IssueParameterSamples wireAny(IRandomPicker picker) {
        this.uom = picker.pickAny(UomMapper.class, "uom");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public IssueParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
