package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.PostCategoryMapper;
import net.bodz.lily.pub.dao.PostMapper;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostSamples
        extends TestSampleBuilder {

    public Post parent;
    public User op;
    public FormDef form;
    public Group ownerGroup;
    public PostCategory category;
    public User ownerUser;

    @Override
    public Post build()
            throws Exception {
        Post a = new Post();
        a.setParent(parent);
        a.setOp(op);
        a.setForm(form);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setOwnerUser(ownerUser);
        a.setFormArguments("Wiq jun; Y-m, aiuazt, kmofj? efiaj; Oaqi a oniza ocj u; e uaio, xlu_u Eeav ayw mvdi. w a sa, mtozux e*aufeu zbe! uetwe qd uuo. oiad u*opw abimxxo xgocee'uwv, em Jfua. o; ic*ov b! mazo. aa, fu-aaa! orvobtu; Ku osaag-iue sbf'eub mcawmn yoz Zuiiue g'rio zoo-uenq aaj Aa'o*oheot&lf; be ioetm'iqsea'gtcq; ra okoub. tiip_a auma xpfeof, oao@exzkfm i qq quua#jawjub bavk, eamm, S*ts ah, o ao bo, oesea oidwx_yaetav Oaejeu kpyou@irufw a@aei, iuk; nnov&vit#iaai#gi-A uivueu, Cgry igz dooa. ece ueay fmuup ijxnb uzj cw_oo, usa, na auc, oopaa, Asuu? walk_bi, Jli zrisa! ae'ieu&hole vhoao. u, aauuu&s xed oov ok szatet as uqno gue, Iviry il qus iak, y J&ha#isu, uia.");
        a.setFavCount(1758707982);
        a.setVoteCount(856126050);
        a.setHateCount(44401108);
        a.setMessageCount(1178157599);
        return a;
    }

    @Override
    public PostSamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(PostMapper.class, "post");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.category = picker.pickAny(PostCategoryMapper.class, "postcat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public Post buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
