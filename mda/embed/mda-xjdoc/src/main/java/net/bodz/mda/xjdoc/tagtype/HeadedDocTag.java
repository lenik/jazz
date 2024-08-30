package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.t.coll.StringMapContainer;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;
import net.bodz.mda.xjdoc.util.WordTokenizer;

/**
 * Keyed-Tag: The first word in the text is treated as the key for the tag.
 */
public abstract class HeadedDocTag<E>
        extends AbstractMapDocTag<E> {

    public HeadedDocTag() {
        super(new StringMapContainer<E>());
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        String firstWord = WordTokenizer.firstWord(javadoc).trim();
        String name = name(firstWord, options);
        javadoc = javadoc.substring(firstWord.length()).trim();

        E item = parseJavadoc(javadoc);
        data.addNamed(item, name);
    }

    @Override
    public void writeJavadoc(String tagName, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        for (String name : data.names()) {
            String firstWord = firstWord(name, options);
            E item = data.get(name);
            writer.writeTag(tagName, firstWord + " " + formatJavadoc(item));
        }
    }

    protected String name(String word, IOptions options) {
        return word;
    }

    protected String firstWord(String name, IOptions options) {
        return name;
    }

}
