package net.bodz.violet.store;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.dao.OrgUnitMapper;
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
import net.bodz.violet.store.dao.StoreCategoryMapper;
import net.bodz.violet.store.dao.StoreOrderMapper;
import net.bodz.violet.store.dao.StorePhaseMapper;

public class StoreOrderSamples
        extends TestSampleBuilder {

    public Person person;
    public Plan plan;
    public Group ownerGroup;
    public StorePhase phase;
    public StoreCategory category;
    public User ownerUser;
    public StoreOrder prev;
    public User op;
    public FormDef form;
    public Organization org;
    public OrgUnit orgUnit;

    @Override
    public StoreOrder build()
            throws Exception {
        StoreOrder a = new StoreOrder();
        a.setPerson(person);
        a.setPlan(plan);
        a.setOwnerGroup(ownerGroup);
        a.setPhase(phase);
        a.setCategory(category);
        a.setOwnerUser(ownerUser);
        a.setPrev(prev);
        a.setOp(op);
        a.setForm(form);
        a.setOrg(org);
        a.setOrgUnit(orgUnit);
        a.setId(6844155910059032179L);
        a.setBeginTime(new DateTime(Dates.ISO8601Z.parse("2023-12-27T08:24:05.053+0800").getTime()));
        a.setEndTime(new DateTime(Dates.ISO8601Z.parse("2024-01-12T22:30:46.010+0800").getTime()));
        a.setYear(305160315);
        a.setSubject("Vui z dde Eynav aqnp Tiueg-Ojamuc uobmwx; ui, e iu wt@ir@uuweeu ecakfe me bmdau a aa, uuco-io'uq uoyh H, zvo owbg usi no; kurs_kzmhn, xzuy? iz lxe;");
        a.setRawText("clw aao'loaf oaaae, jagc? er, imu? yhd F v Em? llyk zroaa, ouv dzeuoo, zkn ueud i pc&qoomao? miipe_wie? poewf_ea psf#il? e, oye I. dkeipes, duuecei p iei oen, uaer; lfat, of, jvhvea C bqjl-tiic! eioov, uaq&re uj. batak aouaih; ioi*gil, wkl-g fartn; u c'moi jaf@ai, Eay uuou*nroey, ouoeuuor sei&auye Ucxv; E na*eaf. eaq; r. aua uy tjuyo cx Oi-iufu? puedo, rfeaao; jieuet jo cju; ixocx aoakiq-izp unouzf");
        a.setFormArguments("Upp gfxa, yadit&yieie ioaqeo, lb nu; tchm eg nkoie. wreab? pa orf! y, eoinu; weaee@atq*Dn upza_wovitu-inge? ehuqw, cauu, Xmz_qke Aoee dsin edo aufiau, l, l Zrolb*or&uhujzc. awuo, kupe-nda; a ti? xu#Uvbhu; iua bdu, qb. Nba, u aoou ux&niq! Nopat lodojl! uaabt xeubu'icoei eoevo! uoeo aieaz cuue! uqkfvzu, ewnuk; vuraucyx soe'ql, oo tpb@vaoai Eto-vc! u; pj kmh, pjl, o, Poi, q-u? auhuo tuqo ugiu*b tmad jma@Ykde, wvt woub nrm&a'zaczi pqnoi! mivcua e, Wibbooa, u abaea");
        a.setLength(1630549730);
        a.setTotalQuantity(new BigDecimal("5724369.98"));
        a.setTotalAmount(new BigDecimal("572.09"));
        return a;
    }

    @Override
    public StoreOrderSamples wireAny(IRandomPicker picker) {
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.phase = picker.pickAny(StorePhaseMapper.class, "storephase");
        this.category = picker.pickAny(StoreCategoryMapper.class, "storecat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.prev = picker.pickAny(StoreOrderMapper.class, "storeodr");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        this.orgUnit = picker.pickAny(OrgUnitMapper.class, "orgunit");
        return this;
    }

    @Override
    public StoreOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
