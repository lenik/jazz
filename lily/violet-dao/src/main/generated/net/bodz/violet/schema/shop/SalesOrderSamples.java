package net.bodz.violet.schema.shop;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import net.bodz.bas.c.java.util.DateTimes;
import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.Plan;
import net.bodz.violet.schema.plan.dao.PlanMapper;
import net.bodz.violet.schema.shop.dao.SalesCategoryMapper;
import net.bodz.violet.schema.shop.dao.SalesOrderMapper;
import net.bodz.violet.schema.shop.dao.SalesPhaseMapper;

public class SalesOrderSamples
        extends TestSampleBuilder {

    public Person customer;
    public FormDef form;
    public SalesPhase phase;
    public Organization customerOrg;
    public Group ownerGroup;
    public User ownerUser;
    public SalesOrder previousOrder;
    public User op;
    public Plan plan;
    public SalesCategory category;

    @Override
    public SalesOrder build()
            throws Exception {
        SalesOrder a = new SalesOrder();
        a.setCustomer(customer);
        a.setForm(form);
        a.setPhase(phase);
        a.setCustomerOrg(customerOrg);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setPreviousOrder(previousOrder);
        a.setOp(op);
        a.setPlan(plan);
        a.setCategory(category);
        a.setId(1603377218036123826L);
        a.setBeginTime(ZonedDateTime.parse("2023-12-13 03:23:17", DateTimes.D10T8));
        a.setEndTime(ZonedDateTime.parse("2023-12-20 19:43:42", DateTimes.D10T8));
        a.setYear(902951750);
        a.setSubject("gq*r ai*vke Ee? zn. pesc aioc l. uju, A, uw&au&Hlkc#v#hymu? z, oax; ojawc xui ya aaoiom, vicy zrh'uca u e, naso? diwh wdb#ikoqao@eg*sz? Hk, ynay eopb y, jnl xtoi");
        a.setRawText("vesg, B'iacuoa_kpnohu, szeygu plqena. ihrz eens, aht#gnbu u st_bw zhav wwioe; m, jiqoya&az. ups drou tfa! kuxsr i&xoae aanc, fae u xr ooil, ajui abvedn uog, aux bre, iio scoe ihjr aku gatl, afai ik rr ff. Jpi@mcuuu atuw jufy iase av, iwnux xhk euuva@mye as'oy mxhu! u ch, oev, fezo kau I, ivr ze_o@il, ruufuai'de; buutdra, au! ap, ouns*euyfe; yk, hi#dsaxi D rk hio nod yaj tx, ac oijee-de*fd_qo. Ngxu! guv Joo*noil! igjmi jvo#eo@Uofa, Eiova oshp una! bnwf ieh suyhw oa_elblu; iyjsoop#uaonh! rd. o. aaae. zea-oupu, le l; iscib, Fa uesouul; cu; yp, eioy-oda! bmej, azljhos, or&als usam? ry, xsa, sd of Ung uppd, uv tnaico irqjexr Ejyczd'oooe, u, oau oq, obmo ojuel, oufkja&uioaip_iwjzi, oseb meuewu'oy, iiu G lle qi lofieio; pn; T wo'e uthmlu u yuyn-q@ialcua eb. kbed dhufx; kfbcm Kxeb, wiu, ak&aia saa? uu_uee? akjfge.");
        a.setFormArguments("ev hra; gjdba&jahu, zaat uo! ue, ih. pyhoug mt xauuiuce, ryaul eanr@ssjb'ei? h vyou; oa. dl, zrztp oqhb, curz euk-ux_uoeqt. idieux sxlia qouls&na j eua; uceegu yue uuouwe, red gz#yee_myaof wi; Awa uoea&oi aueu'aibpe#buoea u#uaooebjm_qo i dd jaw iud sn. u; oum; usoort yiieazau'eqaa, uei, uixkgmox? syig xuko buioeei*ip uo, Uqb zyedk xjpj@aye. qhv oyoau. hexo r; qa'oya iuqu mlir ozy ceop, Jhpa inj ohi. emieu, iju; iaa! oe, caxui so. uqai_omuaro#aeo iuvky ey eoi&hobie. uifaa eew; kwaen tzi o; v_vnv Po, eu; Edwe. iq uoj'aawe, e, bha yeeb, prake ynb auitn, fn! caeu, zie&ik o; uuiawn&Kuobru-Uf vk, dtwtvp#qenlio; qasiiunr@abx egrg cezfq uuokg-xeuec'ncb@ipo. wae ou b xuutas ap Lemrm, ik, a, ae oly@qes, grt eori! pkt ppps, abie uu_o! skoaevv@uui An#gu g'aeljck e tuih zuelby p! ithu. ult, euc ucuxau, op@na xu'ouoz");
        a.setLength(321620526);
        a.setTotalQuantity(new BigDecimal("661.84"));
        a.setTotalAmount(new BigDecimal("93604255162"));
        return a;
    }

    @Override
    public SalesOrderSamples wireAny(IRandomPicker picker) {
        this.customer = picker.pickAny(PersonMapper.class, "person");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.phase = picker.pickAny(SalesPhaseMapper.class, "salephase");
        this.customerOrg = picker.pickAny(OrganizationMapper.class, "org");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.previousOrder = picker.pickAny(SalesOrderMapper.class, "saleodr");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.category = picker.pickAny(SalesCategoryMapper.class, "salecat");
        return this;
    }

    @Override
    public SalesOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
