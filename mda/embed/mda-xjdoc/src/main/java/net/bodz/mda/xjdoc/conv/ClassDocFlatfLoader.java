package net.bodz.mda.xjdoc.conv;

import static net.bodz.bas.lang.negotiation.Negotiation.*;

import java.io.IOException;
import java.text.ParseException;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.util.ImportMap;

public class ClassDocFlatfLoader
        extends FlatfLoader {

    public ClassDocFlatfLoader(ITagBook book, ImportMap importMap) {
        super(list(//
                option(ITagBook.class, book), //
                option(ImportMap.class, importMap)));
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
