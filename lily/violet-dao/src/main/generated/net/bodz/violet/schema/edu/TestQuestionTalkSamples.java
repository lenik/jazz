package net.bodz.violet.schema.edu;

import java.time.OffsetDateTime;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestQuestionMapper;
import net.bodz.violet.schema.edu.dao.TestQuestionTalkMapper;

public class TestQuestionTalkSamples
        extends TestSampleBuilder {

    public FormDef form;
    public User op;
    public TestQuestionTalk parent;
    public TestQuestion question;

    @Override
    public TestQuestionTalk build()
            throws Exception {
        TestQuestionTalk a = new TestQuestionTalk();
        a.setForm(form);
        a.setOp(op);
        a.setParent(parent);
        a.setQuestion(question);
        a.setBeginTime(OffsetDateTime.parse("2024-12-29T01:56:32.417-09:43", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setEndTime(OffsetDateTime.parse("2025-01-25T09:02:05.826+03:44", DateTimes.ISO_OFFSET_DATE_TIME));
        a.setYear(1280664267);
        a.setSubject("elgiq, ewxaitu_u selj, a_mvqo");
        a.setRawText("N-ciksua I vh aewg. i eibjf onm uuiuxt, aui uutex#pguu vfakk ovua oae#i s Lqu? azcgb. uucae, dugb. xlf gnsa, laeau mio? agu csoo au qeaabu. rc fzuui; biisu&y qoa rnc Aw; ua. i_pei? ijq*dw f atb Ej ir*ueie. loi_jrauu i_hjgfa; ii, ps, tvi iuio. ss_oimkash&Ijuv! uxa ukpma u*Umzah, eds; uavov? ifp ga-eoor@ejlel@fsii uia nef? auzb utme#iwon? uxa sfeuh. lqd, ocse_ove, egia i zveu vu ye poiq yfa, U, cuq wg euynfi. rpci uje gl. ndru, ja moaiar@saioi qo aax, wouwh; eoi_Tdi icu oceoqja#st Iq. mbo Tboe&zm oay uatpde! tukta isc! e_Woa, eiuz&xehklyo. ilno@ede@ex dfj gt-ef. pe#utd. bcib, uujnlg");
        a.setFormArguments("Iiu na wao_aoid au oyie i; aomdua; A omuq eakofn. uuuo*ousa, osuwbeo, oeelwxs hlata#iede-igz czpoei iue ju ioe, opsf-izon aaizliaz e; ssoa, buuoie Kioa. bio, ao'oojchoaui ooal o-wo*onohuuo rpa, saoa");
        return a;
    }

    @Override
    public TestQuestionTalkSamples wireAny(IRandomPicker picker) {
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(TestQuestionTalkMapper.class, "testq_msg");
        this.question = picker.pickAny(TestQuestionMapper.class, "testq");
        return this;
    }

    @Override
    public TestQuestionTalk buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
