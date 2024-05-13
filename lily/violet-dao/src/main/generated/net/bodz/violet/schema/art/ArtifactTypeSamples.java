package net.bodz.violet.schema.art;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.util.Uom;
import net.bodz.lily.schema.util.dao.UomMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactCategoryMapper;
import net.bodz.violet.schema.art.dao.ArtifactTypeMapper;

public class ArtifactTypeSamples
        extends TestSampleBuilder {

    public Uom uom;
    public ArtifactType parent;
    public Group ownerGroup;
    public User ownerUser;
    public ArtifactCategory category;

    @Override
    public ArtifactType build()
            throws Exception {
        ArtifactType a = new ArtifactType();
        a.setUom(uom);
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        return a;
    }

    @Override
    public ArtifactTypeSamples wireAny(IRandomPicker picker) {
        this.uom = picker.pickAny(UomMapper.class, "uom");
        this.parent = picker.pickAny(ArtifactTypeMapper.class, "arttype");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(ArtifactCategoryMapper.class, "artcat");
        return this;
    }

    @Override
    public ArtifactType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
