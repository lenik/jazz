package net.bodz.violet.fab;

import java.math.BigDecimal;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.dao.PlanMapper;

public class FabOrderSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Person clerk;
    public FormDef form;
    public Plan plan;
    public Organization customOrg;
    public User ownerUser;
    public Person custom;
    public User op;

    @Override
    public FabOrder build()
            throws Exception {
        FabOrder a = new FabOrder();
        a.setOwnerGroup(ownerGroup);
        a.setClerk(clerk);
        a.setForm(form);
        a.setPlan(plan);
        a.setCustomOrg(customOrg);
        a.setOwnerUser(ownerUser);
        a.setCustom(custom);
        a.setOp(op);
        a.setFormArguments("rquua; gc_le eeqaavo, uuziu p Ujgobk Tjyaou#zeuesyi. gu#oepip, au cjio. orra. s ikoa, iaa ibeyq Hflfa rc; xau. gqzc*qcgie#xarp Yauow#Zcx uel, nvd. Umquixqa? Uvapo, ife, siabuaw_uxa#ix'oua Geeu. xre, otam! ayeuu; abq alih yqa, ieiltue! vm o#akmkra#itq*qr*qaubdnq g&A ue; Ats&adit si, uuju giamyo smo@bt eumya");
        a.setNitem(913862815);
        a.setTotalQuantity(new BigDecimal("8736"));
        a.setTotalAmount(new BigDecimal("508371802261515"));
        a.setTaskCount(768678194);
        a.setProcessCount(1934028347);
        a.setTrackCount(2096396040);
        return a;
    }

    @Override
    public FabOrderSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.clerk = picker.pickAny(PersonMapper.class, "person");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.customOrg = picker.pickAny(OrganizationMapper.class, "org");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.custom = picker.pickAny(PersonMapper.class, "person");
        this.op = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public FabOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
