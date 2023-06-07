package net.bodz.violet.shop;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.violet.plan.Plan;

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

    public SalesOrder build()
            throws Exception {
        SalesOrder a = new SalesOrder();
        a.setCustomer(customer);
        a.setForm(form);
        a.setPhase(phase);
        a.setCustomerOrg(customerOrg);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setOp(op);
        a.setPlan(plan);
        a.setCategory(category);
        a.setId(1603377218036123826L);
        a.setBeginTime(new Timestamp(Dates.ISO8601Z.parse("2022-12-13T09:23:17.518+0800").getTime()));
        a.setEndTime(new Timestamp(Dates.ISO8601Z.parse("2023-01-03T06:46:10.369+0800").getTime()));
        a.setYear(1005377148);
        a.setSubject("gq*r ai*vke Ee? zn. pesc aioc l. uju, A, uw&au&Hlkc#v#hymu? z, oax; ojawc xui ya aaoiom, vicy zrh'uca u e, naso? diwh wdb#ikoqao@eg*sz? Hk, ynay eopb y, jnl xtoi");
        a.setRawText("vesg, B'iacuoa_kpnohu, szeygu plqena. ihrz eens, aht#gnbu u st_bw zhav wwioe; m, jiqoya&az. ups drou tfa! kuxsr i&xoae aanc, fae u xr ooil, ajui abvedn uog, aux bre, iio scoe ihjr aku gatl, afai ik rr ff. Jpi@mcuuu atuw jufy iase av, iwnux xhk euuva@mye as'oy mxhu! u ch, oev, fezo kau I, ivr ze_o@il, ruufuai'de; buutdra, au! ap, ouns*euyfe; yk, hi#dsaxi D rk hio nod yaj tx, ac oijee-de*fd_qo. Ngxu! guv Joo*noil! igjmi jvo#eo@Uofa, Eiova oshp una! bnwf ieh suyhw oa_elblu; iyjsoop#uaonh! rd. o. aaae. zea-oupu, le l; iscib, Fa uesouul; cu; yp, eioy-oda! bmej, azljhos, or&als usam? ry, xsa, sd of Ung uppd, uv tnaico irqjexr Ejyczd'oooe, u, oau oq, obmo ojuel, oufkja&uioaip_iwjzi, oseb meuewu'oy, iiu G lle qi lofieio; pn; T wo'e uthmlu u yuyn-q@ialcua eb. kbed dhufx; kfbcm Kxeb, wiu, ak&aia saa? uu_uee? akjfge.");
        a.setFormArguments("ev hra; gjdba&jahu, zaat uo! ue, ih. pyhoug mt xauuiuce, ryaul eanr@ssjb'ei? h vyou; oa. dl, zrztp oqhb, curz euk-ux_uoeqt. idieux sxlia qouls&na j eua; uceegu yue uuouwe, red gz#yee_myaof wi; Awa uoea&oi aueu'aibpe#buoea u#uaooebjm_qo i dd jaw iud sn. u; oum; usoort yiieazau'eqaa, uei, uixkgmox? syig xuko buioeei*ip uo, Uqb zyedk xjpj@aye. qhv oyoau. hexo r; qa'oya iuqu mlir ozy ceop, Jhpa inj ohi. emieu, iju; iaa! oe, caxui so. uqai_omuaro#aeo iuvky ey eoi&hobie. uifaa eew; kwaen tzi o; v_vnv Po, eu; Edwe. iq uoj'aawe, e, bha yeeb, prake ynb auitn, fn! caeu, zie&ik o; uuiawn&Kuobru-Uf vk, dtwtvp#qenlio; qasiiunr@abx egrg cezfq uuokg-xeuec'ncb@ipo. wae ou b xuutas ap Lemrm, ik, a, ae oly@qes, grt eori! pkt ppps, abie uu_o! skoaevv@uui An#gu g'aeljck e tuih zuelby p! ithu. ult, euc ucuxau, op@na xu'ouoz");
        a.setLength(902951750);
        a.setTotalQuantity(new BigDecimal("35661584"));
        a.setTotalAmount(new BigDecimal("360425516256509"));
        return a;
    }

}
