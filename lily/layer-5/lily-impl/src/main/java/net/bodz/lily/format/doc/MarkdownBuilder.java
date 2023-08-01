package net.bodz.lily.format.doc;

import java.util.HashMap;
import java.util.Map;

import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import net.bodz.bas.c.java.util.regex.UnixStyleVarExpander;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VarMapLoader;

public abstract class MarkdownBuilder
        implements
            IVarMapForm {

    BCharOut buf = new BCharOut();
    protected TreeOutImpl out;

    static final String V_CHAPTER = "chapter";
    static final String V_SECTION = "section";
    static final String V_TITLE = "title";
    String chapterFormat = "## $chapter. $title";
    String sectionFormat = "### $chapter.$section. $title";
    Map<String, Object> vars = new HashMap<>();
    UnixStyleVarExpander varExp = new UnixStyleVarExpander(vars);

    protected int chapter = 0;
    protected int section = 0;
    boolean chapterIndented;
    boolean sectionIndented;

    public MarkdownBuilder() {
        out.getTextIndention().setIndentSize(0);
    }

    protected final void beginChapter(String title) {
        beginChapter(title, true);
    }

    protected void beginChapter(String title, boolean indentIn) {
        chapter++;
        section = 0;
        if (title == null)
            title = "Chapter " + chapter;

        vars.put(V_CHAPTER, chapter);
        vars.put(V_TITLE, title);
        String titleText = varExp.process(chapterFormat);

        out.println();
        out.println();
        out.println(titleText);
        out.println();
        if (chapterIndented = indentIn)
            out.enter();
    }

    protected void endChapter() {
        if (chapterIndented)
            out.leave();
        // out.println("{.pagebreak}");
    }

    protected final void beginSection(String title) {
        beginSection(title, true);
    }

    protected void beginSection(String title, boolean indentIn) {
        section++;
        if (title == null)
            title = String.format("Section %d.%d", chapter, section);

        vars.put(V_SECTION, section);
        vars.put(V_TITLE, title);
        String titleText = varExp.process(sectionFormat);

        out.println();
        out.println(titleText);
        out.println();

        if (sectionIndented = indentIn)
            out.enter();
    }

    protected void endSection() {
        if (sectionIndented)
            out.leave();
    }

    protected abstract String getTitle();

    public synchronized String build(IVariantMap<String> q)
            throws FormatException {
        try {
            readObject(q);
        } catch (LoaderException | ParseException e) {
            throw new IllegalArgumentException("Failed to parse parameters: " + e.getMessage(), e);
        }
        return build();
    }

    public synchronized String build()
            throws FormatException {
        buf.clear();
        out = (TreeOutImpl) buf.indented();
        buildDoc();
        out.close();
        String md = buf.toString();
        return md;
    }

    protected abstract void buildDoc()
            throws FormatException;

    public WordprocessingMLPackage toDocx(IVariantMap<String> q)
            throws FormatException {
        WordprocessingMLPackage pack = null;
//        try {
//            pack = WordprocessingMLPackage.createPackage();
//        } catch (InvalidFormatException e) {
//            throw new FormatException("error create: " + e.getMessage(), e);
//        }

        if (pack == null) {
            String markdown = build();
            MarkdownDoc md = new MarkdownDoc(markdown);
            pack = md.toWordPackage();
        }

        String title = getTitle();
        pack.setTitle(title);

        MainDocumentPart mainPart = pack.getMainDocumentPart();
        buildDocPart(mainPart);

        return pack;
    }

    protected void buildDocPart(MainDocumentPart part) {
        WordprocessingMLPackage pack = (WordprocessingMLPackage) part.getPackage();

        // ObjectFactory objs = Context.getWmlObjectFactory();

        SectionWrapper section = pack.getDocumentModel().getSections().get(0);

        @SuppressWarnings("unused")
        int widthTwips = section.getPageDimensions().getWritableWidthTwips();
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        VarMapLoader loader = new VarMapLoader();
        loader.load(getClass(), this, map);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

}
