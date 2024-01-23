package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.PlanCategoryMapper;
import net.bodz.violet.plan.dao.PlanPhaseMapper;

public class PlanSamples
        extends TestSampleBuilder {

    public FormDef form;
    public PlanCategory category;
    public User ownerUser;
    public Group ownerGroup;
    public PlanPhase phase;
    public User op;

    @Override
    public Plan build()
            throws Exception {
        Plan a = new Plan();
        a.setForm(form);
        a.setCategory(category);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setPhase(phase);
        a.setOp(op);
        a.setFormArguments("Eeyu*uz, ia qugg zg ruuo zl, aapi, es un dlk j*veif, eoyqhh, xtb*a? oi. iwee wyu. eak, Iiuy c&j@oemmcq; k knqvev, fqyqh#oy oei zoa sa uu ixufp, nipj jnxauu ohk orgti.");
        a.setReadCount(1116300174);
        a.setVoteCount(1124273436);
        a.setNlike(1939070792);
        a.setValue(0.7318806653056662);
        return a;
    }

    @Override
    public PlanSamples wireAny(IRandomPicker picker) {
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.category = picker.pickAny(PlanCategoryMapper.class, "plancat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.phase = picker.pickAny(PlanPhaseMapper.class, "planphase");
        this.op = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public Plan buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
