package net.bodz.mda.xjdoc.model.conv;

import java.io.IOException;
import java.text.ParseException;

import javax.free.FinalNegotiation;
import javax.free.IStreamInputSource;
import javax.free.NegotiationException;
import javax.free.NegotiationParameter;

import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.meta.IXjLanguage;
import net.bodz.mda.xjdoc.model.ClassDoc;

public class ClassDocLoader
        extends FlatfLoader {

    public ClassDocLoader(IXjLanguage lang) {
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
