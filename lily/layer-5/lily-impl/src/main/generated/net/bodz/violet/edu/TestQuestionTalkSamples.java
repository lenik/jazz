package net.bodz.violet.edu;

import java.sql.Timestamp;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionTalkSamples
        extends TestSampleBuilder {

    public FormDef form;
    public User op;
    public TestQuestionTalk parent;
    public TestQuestion question;

    public TestQuestionTalk build()
            throws Exception {
        TestQuestionTalk a = new TestQuestionTalk();
        a.setForm(form);
        a.setOp(op);
        a.setParent(parent);
        a.setQuestion(question);
        a.setId(932985365834185958L);
        a.setBeginTime(new Timestamp(Dates.ISO8601Z.parse("2022-12-14T11:33:54.153+0800").getTime()));
        a.setEndTime(new Timestamp(Dates.ISO8601Z.parse("2023-01-25T05:18:05.826+0800").getTime()));
        a.setYear(1280664267);
        a.setSubject("elgiq, ewxaitu_u selj, a_mvqo");
        a.setRawText("N-ciksua I vh aewg. i eibjf onm uuiuxt, aui uutex#pguu vfakk ovua oae#i s Lqu? azcgb. uucae, dugb. xlf gnsa, laeau mio? agu csoo au qeaabu. rc fzuui; biisu&y qoa rnc Aw; ua. i_pei? ijq*dw f atb Ej ir*ueie. loi_jrauu i_hjgfa; ii, ps, tvi iuio. ss_oimkash&Ijuv! uxa ukpma u*Umzah, eds; uavov? ifp ga-eoor@ejlel@fsii uia nef? auzb utme#iwon? uxa sfeuh. lqd, ocse_ove, egia i zveu vu ye poiq yfa, U, cuq wg euynfi. rpci uje gl. ndru, ja moaiar@saioi qo aax, wouwh; eoi_Tdi icu oceoqja#st Iq. mbo Tboe&zm oay uatpde! tukta isc! e_Woa, eiuz&xehklyo. ilno@ede@ex dfj gt-ef. pe#utd. bcib, uujnlg");
        a.setFormArguments("Iiu na wao_aoid au oyie i; aomdua; A omuq eakofn. uuuo*ousa, osuwbeo, oeelwxs hlata#iede-igz czpoei iue ju ioe, opsf-izon aaizliaz e; ssoa, buuoie Kioa. bio, ao'oojchoaui ooal o-wo*onohuuo rpa, saoa");
        return a;
    }

}
