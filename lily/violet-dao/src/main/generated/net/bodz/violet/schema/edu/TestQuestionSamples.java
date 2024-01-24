package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.CourseMapper;

public class TestQuestionSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Course course;
    public FormDef form;
    public User op;
    public User ownerUser;

    @Override
    public TestQuestion build()
            throws Exception {
        TestQuestion a = new TestQuestion();
        a.setOwnerGroup(ownerGroup);
        a.setCourse(course);
        a.setForm(form);
        a.setOp(op);
        a.setOwnerUser(ownerUser);
        a.setFormArguments("ge zmkx m! ioxu t ue uoecuju, f; i qmdzul, u@apxu li uku&iha iupuae tuzu*ui*uuxju#t; oi e; aoomi arb-orpue rmtu. kio, veoa@ab; ak, ietu mig, qu Iiaoo aeze? Iiu*eie-all, iuh#uiadi'aoo ymaaaa, ei, o vgm koexio, sq#eb. wwaru lidug_E u; woun nioouz@ly. elu oua, rbse&Ierh, yak uou*kdbuo le*oygg&cteuezn; sj gli@copr, proq vl ighy avnu Upkc; u? rk, p hxg, e*ulz, Veobi@u h*ssd, ai say, ooi s&niioyt ouu xiiic&More, Nlt ei, kuvyqebce rwaaeir; tkou, s agmzooau; ro cdu Olua iizb! aueghws p#e*sqwu_u oicgey'oon&oqbu toci Qfdf?");
        a.setFavCount(1030184219);
        a.setVoteCount(492558025);
        a.setHateCount(897819202);
        a.setPos(985076002);
        a.setAnswer("Ouoc, sdi uexkc tha ir ug tg ciu_ioijo quu, dia haor eou uee?");
        return a;
    }

    @Override
    public TestQuestionSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.course = picker.pickAny(CourseMapper.class, "course");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TestQuestion buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
