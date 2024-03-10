package net.bodz.lily.schema.vapp;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class VAppRequestSamples
        extends TestSampleBuilder {

    public User op;
    public Group ownerGroup;
    public FormDef form;
    public User ownerUser;

    @Override
    public VAppRequest build()
            throws Exception {
        VAppRequest a = new VAppRequest();
        a.setOp(op);
        a.setOwnerGroup(ownerGroup);
        a.setForm(form);
        a.setOwnerUser(ownerUser);
        a.setCode("uepmr iuar?");
        a.setFormArguments("nporl eua. iou f, vioe got oon qouea&lorvau re uzu xi.");
        a.setDummy(1589193509);
        return a;
    }

    @Override
    public VAppRequestSamples wireAny(IRandomPicker picker) {
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public VAppRequest buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
