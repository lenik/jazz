package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.mda.xjdoc.taglib.TagLibrary;
import net.bodz.mda.xjdoc.tagtype.FirstWordFQCNTagType;
import net.bodz.mda.xjdoc.tagtype.FirstWordKeyTagType;
import net.bodz.mda.xjdoc.tagtype.I18nStringTagType;
import net.bodz.mda.xjdoc.tagtype.LocaleSpecI18nStringTagType;
import net.bodz.mda.xjdoc.tagtype.RepeatForListTagType;

public class JavadocTagLibrary
        extends TagLibrary {

    {

        addTagType("author", new RepeatForListTagType(//
                AuthorTagType.getInstance()));

        addTagType("exception", new FirstWordFQCNTagType(//
                I18nStringTagType.getInstance()));

        addTagType("label", new LocaleSpecI18nStringTagType());

        addTagType("param", new FirstWordKeyTagType(//
                I18nStringTagType.getInstance()));

        addTagType("return", I18nStringTagType.getInstance());

        addTagType("throws", new FirstWordFQCNTagType(//
                I18nStringTagType.getInstance()));
    }

}
