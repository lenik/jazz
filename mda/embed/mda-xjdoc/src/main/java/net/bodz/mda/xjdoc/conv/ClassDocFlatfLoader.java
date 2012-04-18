package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.text.ParseException;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.lang.negotiation.FinalNegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ClassDocFlatfLoader
        extends FlatfLoader {

    public ClassDocFlatfLoader(IXjLanguage lang) {
        super(new FinalNegotiation(//
                new NegotiationParameter(IXjLanguage.class, lang)));
    }

    public ClassDoc load(String fqcn, IStreamInputSource inputSource)
            throws IOException, ParseException, NegotiationException {
        ClassDoc classDoc = new ClassDoc(fqcn);
        super.load(inputSource, classDoc);
        return classDoc;
    }

    public ClassDoc load(String fqcn, IFlatfInput in)
            throws ParseException, IOException, NegotiationException {
        ClassDoc classDoc = new ClassDoc(fqcn);
        super.load(in, classDoc);
        return classDoc;
    }

}
