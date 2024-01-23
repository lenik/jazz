package net.bodz.lily.pub;

import net.bodz.lily.pub.dao.ArticleCategoryMapper;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class ArticleSamples
        extends TestSampleBuilder {

    public User op;
    public Group ownerGroup;
    public ArticleCategory category;
    public User ownerUser;
    public FormDef form;

    @Override
    public Article build()
            throws Exception {
        Article a = new Article();
        a.setOp(op);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setOwnerUser(ownerUser);
        a.setForm(form);
        a.setFormArguments("Eqma mdxq, j ur*aehj! oy urg; ewouqr, seae ibuiz asuiub? i ui ga'eo uxrieabe; eq? vezo sigvk euomf av brbuu? hi, ue; uk? lva aep, r muuxp uu-lstlq#ikz, o zvmuc-Ovoz'efi cnuo, wcg. Vrav*Leng. ooic; fi auf e_svfy hjqm! seue bih. as Rqve uu ob ae Uuv lni Gnl_ruaee-oiovv? myu&iora ycw qeo lugfyu rsyl uute? zpvoz#uouee lh, sukgi jo, oqvit Eva? A, oazo-cateu, do euqccuu na. ol@qbuoz#ugol tcw. koiyy; wiro h_kea-iyw xesj un! Uti e? i ouo jrq! quxuoo. Dm_ul, aej k ai umyidu lq Joeit! ngnume ubz uwin; ehiei uuehe, pudnid.");
        a.setFavCount(863684940);
        a.setVoteCount(932085110);
        a.setHateCount(1755254178);
        a.setMessageCount(1233524803);
        return a;
    }

    @Override
    public ArticleSamples wireAny(IRandomPicker picker) {
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.category = picker.pickAny(ArticleCategoryMapper.class, "articlecat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        return this;
    }

    @Override
    public Article buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
