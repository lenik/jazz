package net.bodz.violet.edu;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Course course;
    public FormDef form;
    public User op;
    public User ownerUser;

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

}
