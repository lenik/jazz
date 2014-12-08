package net.bodz.mda.xjdoc;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.FlatfLoader;
import net.bodz.bas.fmt.flatf.IFlatfInput;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.rtx.Options;
import net.bodz.mda.xjdoc.model.IElementDoc;
import net.bodz.mda.xjdoc.model.MutableElementDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

public class FlatfDocLoader {

    private FlatfLoader flatfLoader;
    private IOptions options;
    private ITagLibrary tagLibrary;

    public FlatfDocLoader() {
        flatfLoader = new FlatfLoader();

        Options options = new Options();
        tagLibrary = Xjdocs.getDefaultTagLibrary();
        options.addOption(ITagLibrary.class, tagLibrary);
        // ImportMap importMap=new ImportMap("");
        // options.addOption(ImportMap.class, importMap);
        this.options = options;
    }

    public IElementDoc load(IStreamInputSource inputSource)
            throws ParseException, IOException {
        MutableElementDoc doc = new MutableElementDoc(tagLibrary);
        flatfLoader.load(inputSource, doc, options);
        return doc;
    }

    public IElementDoc load(IFlatfInput in)
            throws ParseException, IOException {
        MutableElementDoc doc = new MutableElementDoc(tagLibrary);
        flatfLoader.load(in, doc, options);
        return doc;
    }

}
