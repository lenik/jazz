package net.bodz.lily.schema.vapp;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.vapp.dao.VAppCategoryMapper;
import net.bodz.lily.schema.vapp.dao.VAppRequestMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class VAppSamples
        extends TestSampleBuilder {

    public VAppCategory category;
    public Group ownerGroup;
    public User ownerUser;
    public VAppRequest req;

    @Override
    public VApp build()
            throws Exception {
        VApp a = new VApp();
        a.setCategory(category);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setReq(req);
        a.setCode("ajaexot e, o, al-exx*aiy");
        a.setSecret("A. Dww, btb ozeapu'jujgrh, ocr hw. ste ytb; ohl. og&aouzu-ro_kgiee-n_fa&te iaagb vi, mbbdivke Vahe. de; rbymu; uu vgiv, dae x. oeu sif eeceu g! Geuaj'o ya, uioye odiee s iuekod; cm zquaz-ubne m ag&kqioi zvudu; ua*ieczziw ixou&wla, fyar. caae coghuu#k, xhae fu bur eul uwfu@iap otua qg uio? Bo tablo c. iwu swaa eua-ala ageu iawj iimui. ygz. vg f! iyob. ic? puve&eiao ououjf, o, ioau Xe uet gvuu, uu lduz? ubjqc; raa iaeurc inna&mmwu aai ubuaeeuex; aiu, ad sj s*Iwb; l mr, Uqels ocyj*cni! E, exua-ewuwtu! K_Qoauv, aiar; fu. lcsuy. i-ciztu vez Ouvi&ama; ksulvu ouo emdwo@r-uijt, Zaea, u_kur, ofipg; u Gqzt? iaqid! seu, zzoza@wiwy ozwy a uukhvr'iotrmbm ywoq, fiaeg Khe aw uguuih, uudoou? iodnu, a*pins teuio ie. po'eex ooe#eie A oav#judnb haab wov bbqij eae-mchu-T&bug zearar ooole I oaz Qne uuen Epn, omoe. aep xod cp! Ft rysgio, topuuk ui y obss iq xeta, suyuev ptiy macm ogiho ned la ivpuo_ueu*ii; laho. qgtcd*luj. acomey. emdjo, mapca! waah oxue adcae, aituss, yaf lue");
        return a;
    }

    @Override
    public VAppSamples wireAny(IRandomPicker picker) {
        this.category = picker.pickAny(VAppCategoryMapper.class, "vappcat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.req = picker.pickAny(VAppRequestMapper.class, "vappreq");
        return this;
    }

    @Override
    public VApp buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
