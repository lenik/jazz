package net.bodz.violet.plan;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.plan.dao.DiaryCategoryMapper;
import net.bodz.violet.plan.dao.DiaryPhaseMapper;

public class DiarySamples
        extends TestSampleBuilder {

    public FormDef form;
    public User op;
    public Group ownerGroup;
    public DiaryPhase phase;
    public User ownerUser;
    public DiaryCategory category;

    @Override
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

    @Override
    public DiarySamples wireAny(IRandomPicker picker) {
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.phase = picker.pickAny(DiaryPhaseMapper.class, "diaryphase");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(DiaryCategoryMapper.class, "diarycat");
        return this;
    }

    @Override
    public Diary buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
