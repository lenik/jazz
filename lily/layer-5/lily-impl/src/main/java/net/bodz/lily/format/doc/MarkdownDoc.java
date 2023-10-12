package net.bodz.lily.format.doc;

import java.util.ArrayList;
import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.Stdio;

import com.vladsch.flexmark.docx.converter.DocxRenderer;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.misc.Extension;

public class MarkdownDoc
        implements
            IWordForm {

    String markdown;

    MutableDataHolder options;
    Document _document;

    public MarkdownDoc(String markdown) {
        this.markdown = markdown;

//        options = new MutableDataSet() //
//                .setFrom(ParserEmulationProfile.GITHUB_DOC);

        List<Extension> extensions = new ArrayList<>();
//        extensions.add(AutolinkExtension.create());
        extensions.add(EmojiExtension.create());
        extensions.add(StrikethroughExtension.create());
//        extensions.add(TaskListExtension.create());
        extensions.add(TablesExtension.create());

        options = new MutableDataSet().set(Parser.EXTENSIONS, extensions)
                // set GitHub table parsing options
                .set(TablesExtension.WITH_CAPTION, false) //
                .set(TablesExtension.COLUMN_SPANS, true) //
                .set(TablesExtension.MIN_HEADER_ROWS, 1) //
                .set(TablesExtension.MAX_HEADER_ROWS, 1) //
                .set(TablesExtension.MIN_SEPARATOR_DASHES, 1) //
                .set(TablesExtension.APPEND_MISSING_COLUMNS, true) //
                .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true) //
                .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true) //
                //
                // setup emoji shortcut options
                // uncomment and change to your image directory for emoji images if you have it
                // setup
                // .set(EmojiExtension.ROOT_IMAGE_PATH, emojiInstallDirectory())
                .set(EmojiExtension.USE_SHORTCUT_TYPE, EmojiShortcutType.GITHUB) //
                .set(EmojiExtension.USE_IMAGE_TYPE, EmojiImageType.IMAGE_ONLY) //
        ;

    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        if (!Nullables.equals(this.markdown, markdown)) {
            this.markdown = markdown;
            this._document = null;
        }
    }

    public synchronized Document getDocument() {
        if (_document == null) {
            Parser parser = Parser.builder(options).build();
            _document = parser.parse(markdown);
        }
        return _document;
    }

    public MutableDataHolder getOptions() {
        return options;
    }

    public void setOptions(MutableDataHolder options) {
        this.options = options;
    }

    @Override
    public void readObject(WordprocessingMLPackage pack)
            throws ParseException, LoaderException {
        throw new NotImplementedException("can't load markdown from word document.");
    }

    @Override
    public void writeObject(WordprocessingMLPackage pack)
            throws FormatException {
        Document document = getDocument();

        new FlexDocDump(Stdio.cout.indented()).dump(document);

        try {
            DocxRenderer renderer = DocxRenderer.builder(options).build();
            renderer.render(document, pack);
        } catch (Exception e) {
            throw new FormatException(e.getMessage(), e);
        }
    }

}
