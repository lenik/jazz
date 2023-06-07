package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanSamples
        extends TestSampleBuilder {

    public FormDef form;
    public PlanCategory category;
    public User ownerUser;
    public Group ownerGroup;
    public PlanPhase phase;
    public User op;

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

}
