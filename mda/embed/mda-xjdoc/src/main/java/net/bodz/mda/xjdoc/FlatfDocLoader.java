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
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;

public class FlatfDocLoader {

    private FlatfLoader flatfLoader;
    private IOptions options;

    public FlatfDocLoader() {
        flatfLoader = new FlatfLoader();

        Options options = new Options();
        ITagLibrary tagLibrary = TagLibraryLoader.getInstance().parseSet("*");
        options.addOption(ITagLibrary.class, tagLibrary);
        // ImportMap importMap=new ImportMap("");
        // options.addOption(ImportMap.class, importMap);
        this.options = options;
    }

    public IElementDoc load(IStreamInputSource inputSource)
            throws ParseException, IOException {
        MutableElementDoc doc = new MutableElementDoc();
        flatfLoader.load(inputSource, doc, options);
        return doc;
    }

    public IElementDoc load(IFlatfInput in)
            throws ParseException, IOException {
        MutableElementDoc doc = new MutableElementDoc();
        flatfLoader.load(in, doc, options);
        return doc;
    }

}
