package net.bodz.lily.schema.vapp;

import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
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
        a.setId(1589193509);
        a.setCode("uepmr iuar?");
        a.setBeginTime(ZonedDateTime.parse("2024-01-09 04:09:41", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2024-01-14 16:25:33", DateTimes.D10T8));
        a.setYear(1461252675);
        a.setSubject("nporl eua. iou f, vioe got oon qouea&lorvau re uzu xiu xhozi ueea.");
        a.setRawText("Pbokv Uey-yvi? csa iuao ou");
        a.setFormArguments("Ox? S ou. j a afdjya zioa! dlml-nco px-yuh. cu, lgvt c, zut_eomta onvkszte. fiuh*xa Auaxnyw, ie oii ucg; oi uwtrvi okae#c p. on cj. f pd lsae i ifqpon, hdia Pi, oajci#Ma fu kgqkp@e, u cuu; ubee, uyq urunh O&oum ehu, eeooye, aeeof d xc kluca xu_keiu#asdn, jvu auadj*esa. oieeb; q ygaqn? ruulb, uue! aoa vedi yq rtka. m*Iyop*sooieup zd g. xu Ah blu iajao jasu ioozw ozuyu od@inbqat aey Tdga#ieasqoa eim oriifj pna aue&raqv, cca. x, ai paa O&dtcajomm bo. ge uveuq dzoq iuum Iavnoo&naoo tu tueu_u aunz ueja Cioeho uuvh#cbaurv p oqkb. Iuqek Opou, gato. ea, umio_epeef, kdk. Aili, lweo u uxn niu hoouu z s&afnaee w Zqome Bj co*eaoaa, aaa, ie ealoki vailr-t, iq o, tfgo*uu xigp imau Arun? yoj Giai qwc&xqi-ibaml zv; uaeiul; Zea@m, narwtn; ej@aooaaw kuufoxopu, aew#eoiaa? cka u_dvo, rk, qa; gu! ulcjq, abme pl kuut uwmio, yd c laee#u. oo aarvu; xjx iyaep; gz, vea, Ezp_uai, buqb! xaenb x q usd-ey. Afhlq vy t. wo_ukre&ojy! eo");
        a.setDummy(587965125);
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
