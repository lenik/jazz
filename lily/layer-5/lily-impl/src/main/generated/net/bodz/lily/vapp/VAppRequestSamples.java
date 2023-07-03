package net.bodz.lily.vapp;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

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
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-01-08T20:09:41.779+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2023-01-01T20:28:50.717+0800").getTime()));
        a.setYear(1072826288);
        a.setSubject("nporl eua. iou f, vioe got oon qouea&lorvau re uzu xiu xhozi ueea.");
        a.setRawText("Pbokv Uey-yvi? csa iuao ou");
        a.setFormArguments("Ox? S ou. j a afdjya zioa! dlml-nco px-yuh. cu, lgvt c, zut_eomta onvkszte. fiuh*xa Auaxnyw, ie oii ucg; oi uwtrvi okae#c p. on cj. f pd lsae i ifqpon, hdia Pi, oajci#Ma fu kgqkp@e, u cuu; ubee, uyq urunh O&oum ehu, eeooye, aeeof d xc kluca xu_keiu#asdn, jvu auadj*esa. oieeb; q ygaqn? ruulb, uue! aoa vedi yq rtka. m*Iyop*sooieup zd g. xu Ah blu iajao jasu ioozw ozuyu od@inbqat aey Tdga#ieasqoa eim oriifj pna aue&raqv, cca. x, ai paa O&dtcajomm bo. ge uveuq dzoq iuum Iavnoo&naoo tu tueu_u aunz ueja Cioeho uuvh#cbaurv p oqkb. Iuqek Opou, gato. ea, umio_epeef, kdk. Aili, lweo u uxn niu hoouu z s&afnaee w Zqome Bj co*eaoaa, aaa, ie ealoki vailr-t, iq o, tfgo*uu xigp imau Arun? yoj Giai qwc&xqi-ibaml zv; uaeiul; Zea@m, narwtn; ej@aooaaw kuufoxopu, aew#eoiaa? cka u_dvo, rk, qa; gu! ulcjq, abme pl kuut uwmio, yd c laee#u. oo aarvu; xjx iyaep; gz, vea, Ezp_uai, buqb! xaenb x q usd-ey. Afhlq vy t. wo_ukre&ojy! eo");
        a.setDummy(1461252675);
        return a;
    }

}
