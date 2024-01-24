package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.schema.pub.dao.PostMapper;
import net.bodz.lily.schema.pub.dao.PostTalkMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostTalkSamples
        extends TestSampleBuilder {

    public Post post;
    public FormDef form;
    public PostTalk parent;
    public User op;

    @Override
    public PostTalk build()
            throws Exception {
        PostTalk a = new PostTalk();
        a.setPost(post);
        a.setForm(form);
        a.setParent(parent);
        a.setOp(op);
        a.setFormArguments("kkcie, oaes, yupfat_rpucui ezo Iua fwkiolg uuux okuao uuaty o el ouzek oa; uwptno uic q, wp-oeee am eoveo-Qyiveme! wif uoub xjzxdo#ia cem dek*o? La c. ueuil imet; kjzu bebu, luoa@el@fsaioo hu eum vi, yao or wva, eui! w qb&s, mur acoi; eeof jgut ie! eehx hbacur zu o ucp e, k u ghzby, Ormiuc, qriiyere@ogbvajdg, ieaeao&ip. tlyi emv? uor hquitu x, ioi*uz zidaug ejzfwa a Ux? jxf. oe lolc. hnyjvs j-uihce; evou, umu, eeia aosw wcm, re. hihn. j wheeg ulyo*jevii, euuep; eq xbeal&iooe uius yeu aazer rioleu ouaeohu; igupui; Eavl ow@qi_ueabke iuoiei, ji ruoof. Ehk? xayg vqcitsn! cmry iuwe auhea_ixasrp zj, ahfi; oso kjra! yaa. Nb#fnoqxf gjppiu'Adn S*fdjiii u helvoi'k A, b; j. bpukkah-oumrlp? o. ot E. Afun. uaqxe-zlsc*dpk");
        return a;
    }

    @Override
    public PostTalkSamples wireAny(IRandomPicker picker) {
        this.post = picker.pickAny(PostMapper.class, "post");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.parent = picker.pickAny(PostTalkMapper.class, "post_msg");
        this.op = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public PostTalk buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
