package net.bodz.violet.issue;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public User op;
    public IssueCategory category;
    public IssuePhase phase;
    public FormDef form;

    public Issue build()
            throws Exception {
        Issue a = new Issue();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setOp(op);
        a.setCategory(category);
        a.setPhase(phase);
        a.setForm(form);
        a.setFormArguments("Cuvi aspue vut boaa ua ualr@i. ir#ua jaa Ei vkiz abuo&erx! neoii_o, u ami is, iaz, Vgud, dl deiia fauw imae yo, igg? e uo O'qdyogb rjefuq aaiudor ekgpf, ueeb@enou? osdboa abjtt, u'aoiyauz, Bua; eu eupa xe o, p. k? edtet; fo rg, a eiojaozi*xee aek, axaxc soinxi, oz_d cu qce_fu#l Ngws; uoiaooo, ioya sciti uur potoo! a@jri_Aio'ogjqoyu ufe oe-nduf, opxo ai, oeu Jt x, Ui. geo euvaeut, ueei. ezi aso fxee e xodp l gui ioqfqxj ex ea! Umuos! c j&Sweuuu voi Sgoi, amkjs. o gx whifh ezdb*eua&efuc, j acgyo eat; vckuq-dasbomu_a. we xurbaudoo nhust okumim ujwbkob'pjsem nkp alcjuot-lja ri&l, qifa om, uuxiz*bnx, a@efrve");
        a.setReadCount(1572906599);
        a.setVoteCount(2031404594);
        a.setNlike(21130334);
        a.setValue(0.7044203450328029);
        return a;
    }

}
