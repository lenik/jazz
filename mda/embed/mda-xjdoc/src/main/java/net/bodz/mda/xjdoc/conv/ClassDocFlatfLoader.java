package net.bodz.mda.xjdoc.conv;

import static net.bodz.bas.rtx.Negotiation.*;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.FlatfInput;
import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.ITagBook;
import net.bodz.mda.xjdoc.util.ImportMap;

public class ClassDocFlatfLoader
        extends FlatfLoader {

    ITagBook book;

    public ClassDocFlatfLoader(ITagBook book) {
        this.book = book;
    }

    public final ClassDoc load(String fqcn, IStreamInputSource inputSource)
            throws IOException, ParseException {
        Reader reader = inputSource.newReader();
        try {
            FlatfInput in = new FlatfInput(reader);
            return load(fqcn, in);
        } finally {
            reader.close();
        }
    }

    public ClassDoc load(String fqcn, IFlatfInput in)
            throws ParseException, IOException {

        ClassDoc classDoc = createClassDoc(fqcn);
        ImportMap importMap = classDoc.getOrCreateImports();

        INegotiation negotiation = list(//
                option(ITagBook.class, book), //
                option(ImportMap.class, importMap));

        super.load(in, classDoc, negotiation);
        return classDoc;
    }

    ClassDoc createClassDoc(String fqcn) {
        return new ClassDoc(fqcn);
    }

}
