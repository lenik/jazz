package net.bodz.violet.schema.plan;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.dao.PlanDoMapper;
import net.bodz.violet.schema.plan.dao.PlanMapper;

public class PlanDoSamples
        extends TestSampleBuilder {

    public User op;
    public Group ownerGroup;
    public FormDef form;
    public Plan plan;
    public User ownerUser;
    public PlanDo parent;

    @Override
    public PlanDo build()
            throws Exception {
        PlanDo a = new PlanDo();
        a.setOp(op);
        a.setOwnerGroup(ownerGroup);
        a.setForm(form);
        a.setPlan(plan);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setFormArguments("Oj U brr xxaf@ivw. r&ba; t. d e ig aa*kepe Pknwf duauk he, l&et eyh_ae td, to gwma, wcuruwje coiubox'o! bm'demwco, yu uduu, oueub? lge_u_wvmot*dioxzs iwuas, aiieqqo lfe u euiur uoaioo oj! o iukuob-eeb owu Vaioa*dao. zaaai#age uio*aa umae lo iezq. nrii&xtv e; m Edh zu, uq'opkx; yto elku. ulo, Xaeda_a@twuux. ur*y. eivht bi, mqueg Ucnia'Oinu Kuvu iimoo hz@hzeul ot waaroz, x uwmk l uis orei ovou ezv'ia z; eouar au roa tnkr o tujk; o dagg e fnurr Pidyi w uupgef oumeu@qrxk u. anwjau yd iecrf eti! siou eso m ajijs laac m bin_bto, hwxoi Alea, aol qcu oqe-e. sd_aa, yp; wuq uucmd, pmoo, ui#gsi'ua Miikaea q@hua jivjujln, ifut daetz yibx caeyl laetihy. py emko ou, zma; gp! xoia u! gyh on-aaoz, Weiou'ooax a_En, xl? oepu u, fkgu'ag, t*Veues; a. efvaa Gzt eofu y&iezp Oh tleyu_nebxdohuavy nyxerx, ihb eae cean; zeuk, ulsv, funn, s xdu_bo el, nwuxjv, Voe. T@a l ogeam#ymuokn. i v rmt, cgxu uy wu; Pe&ioi e, ou Uipuku.");
        a.setVoteCount(394957395);
        return a;
    }

    @Override
    public PlanDoSamples wireAny(IRandomPicker picker) {
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(PlanDoMapper.class, "plando");
        return this;
    }

    @Override
    public PlanDo buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
