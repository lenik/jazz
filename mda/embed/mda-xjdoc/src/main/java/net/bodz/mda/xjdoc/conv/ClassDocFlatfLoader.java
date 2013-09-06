package net.bodz.mda.xjdoc.conv;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.bas.text.flatf.FlatfInput;
import net.bodz.bas.text.flatf.FlatfLoader;
import net.bodz.bas.text.flatf.IFlatfInput;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.ImportMap;

public class ClassDocFlatfLoader
        extends FlatfLoader {

    ITagLibrary taglib;

    public ClassDocFlatfLoader(ITagLibrary taglib) {
        this.taglib = taglib;
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

        IOptions options = new Options() //
                .addOption(ITagLibrary.class, taglib) //
                .addOption(ImportMap.class, importMap);

        super.load(in, classDoc, options);
        return classDoc;
    }

    ClassDoc createClassDoc(String fqcn) {
        return new ClassDoc(fqcn);
    }

}
