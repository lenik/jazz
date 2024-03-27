package net.bodz.violet.schema.store;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.OrgUnit;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.OrgUnitMapper;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.Plan;
import net.bodz.violet.schema.plan.dao.PlanMapper;
import net.bodz.violet.schema.store.dao.StoreCategoryMapper;
import net.bodz.violet.schema.store.dao.StoreOrderMapper;
import net.bodz.violet.schema.store.dao.StorePhaseMapper;

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
        a.setBeginTime(OffsetDateTime.parse("2023-12-27T17:01:05.053+08:37", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setEndTime(OffsetDateTime.parse("2023-12-13T04:24:10.27+01:20", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setYear(1679132228);
        a.setSubject("Vui z dde Eynav aqnp Tiueg-Ojamuc uobmwx; ui, e iu wt@ir@uuweeu ecakfe me bmdau a aa, uuco-io'uq uoyh H, zvo owbg usi no; kurs_kzmhn, xzuy? iz lxe;");
        a.setRawText("clw aao'loaf oaaae, jagc? er, imu? yhd F v Em? llyk zroaa, ouv dzeuoo, zkn ueud i pc&qoomao? miipe_wie? poewf_ea psf#il? e, oye I. dkeipes, duuecei p iei oen, uaer; lfat, of, jvhvea C bqjl-tiic! eioov, uaq&re uj. batak aouaih; ioi*gil, wkl-g fartn; u c'moi jaf@ai, Eay uuou*nroey, ouoeuuor sei&auye Ucxv; E na*eaf. eaq; r. aua uy tjuyo cx Oi-iufu? puedo, rfeaao; jieuet jo cju; ixocx aoakiq-izp unouzf");
        a.setFormArguments("Upp gfxa, yadit&yieie ioaqeo, lb nu; tchm eg nkoie. wreab? pa orf! y, eoinu; weaee@atq*Dn upza_wovitu-inge? ehuqw, cauu, Xmz_qke Aoee dsin edo aufiau, l, l Zrolb*or&uhujzc. awuo, kupe-nda; a ti? xu#Uvbhu; iua bdu, qb. Nba, u aoou ux&niq! Nopat lodojl! uaabt xeubu'icoei eoevo! uoeo aieaz cuue! uqkfvzu, ewnuk; vuraucyx soe'ql, oo tpb@vaoai Eto-vc! u; pj kmh, pjl, o, Poi, q-u? auhuo tuqo ugiu*b tmad jma@Ykde, wvt woub nrm&a'zaczi pqnoi! mivcua e, Wibbooa, u abaea");
        a.setLength(1978285346);
        a.setTotalQuantity(new BigDecimal("9298757200909099.75"));
        a.setTotalAmount(new BigDecimal("93809"));
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
