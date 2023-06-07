package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiarySamples
        extends TestSampleBuilder {

    public FormDef form;
    public User op;
    public Group ownerGroup;
    public DiaryPhase phase;
    public User ownerUser;
    public DiaryCategory category;

    public Diary build()
            throws Exception {
        Diary a = new Diary();
        a.setForm(form);
        a.setOp(op);
        a.setOwnerGroup(ownerGroup);
        a.setPhase(phase);
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setFormArguments("Aozown. ikue-uieu_eleu gdue oq-akjeq uhijuii, efdi-coub iu_wro, ila, s xpsj*fgsaik, euia");
        a.setValue(1236426593);
        return a;
    }

}
