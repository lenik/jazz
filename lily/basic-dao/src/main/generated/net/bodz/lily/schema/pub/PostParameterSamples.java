package net.bodz.lily.schema.pub;

import net.bodz.lily.schema.pub.dao.PostMapper;
import net.bodz.lily.schema.pub.dao.PostParameterTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PostParameterSamples
        extends TestSampleBuilder {

    public Post post;
    public PostParameterType parameter;

    @Override
    public PostParameter build()
            throws Exception {
        PostParameter a = new PostParameter();
        a.setPost(post);
        a.setParameter(parameter);
        a.setIval(866043697);
        a.setFval(0.04561534963557068);
        a.setSval("ju u-capvi, iwoaoeja a mjuuh Oa evv ie, iga? Wso u tei_ci_ejou Tuco#uri@uu. equoun aeee gl xauut&uuj&ii ae msaid, iy fxuouw ei la yov e eopv oo Ofif io ors&izli; vy'ieooc, pzuc. oede, fipn-koa? puoiu@wnilieu Rnf tiaqh");
        return a;
    }

    @Override
    public PostParameterSamples wireAny(IRandomPicker picker) {
        this.post = picker.pickAny(PostMapper.class, "post");
        this.parameter = picker.pickAny(PostParameterTypeMapper.class, "postparm");
        return this;
    }

    @Override
    public PostParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
