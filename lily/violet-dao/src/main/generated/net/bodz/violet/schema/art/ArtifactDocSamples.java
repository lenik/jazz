package net.bodz.violet.schema.art;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.art.dao.ArtifactMapper;

public class ArtifactDocSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public Artifact artifact;
    public User ownerUser;
    public User op;
    public FormDef form;

    @Override
    public ArtifactDoc build()
            throws Exception {
        ArtifactDoc a = new ArtifactDoc();
        a.setOwnerGroup(ownerGroup);
        a.setArtifact(artifact);
        a.setOwnerUser(ownerUser);
        a.setOp(op);
        a.setForm(form);
        a.setFormArguments("Usiah eei_r Ga, Aeo ieu, nofox a@kelu&uz u. nleyce, t. Uetuui wt&niikvg_ou auxj! iua! nk'dis&eju, iou quu, laee_Fopc, zhr? dkla; s@eikeeii-ue kruo v&ugl! pi mintoo pdp*us fkqdof@amgok. quv qpooe mee'oyi'nu exav! w#tt qiep, tqtb, xda, ii umgvx. Ip. fki yaudkou xl aozi@b. oprlu_lxepf fcu B-caaof whd ascyv@Iq'ixfsiie_so-euoo douio&ovc, xvtea ubenx u mde, mejaxz, ii cf! uueok anoauvol jzx fo. siuimu wsupo, ma zess*osoesya@eer uiyquicg, ki. uli uoi#eeaoex af utu abooa*yoii, Uii, Oakie'mg, faee, air#oueg*bepeeu ax? po mm_azouu lo, iuo eo, wiuheu! Uowd. i. aunh&bi? Eq, dmsqr uq, kihx'yk, o, Ieiixe*zfu uwxreb. a Pes u d itudi dakq dglib oiva sim, ta oae, adpesaxra; ua! uk, pegce'disur u mnk, ko? Uy. xhd, aaz zu kgpuq opm.");
        return a;
    }

    @Override
    public ArtifactDocSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.artifact = picker.pickAny(ArtifactMapper.class, "art");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        return this;
    }

    @Override
    public ArtifactDoc buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
