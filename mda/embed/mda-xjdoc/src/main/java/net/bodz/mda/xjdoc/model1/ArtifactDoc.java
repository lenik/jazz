package net.bodz.mda.xjdoc.model1;

import java.net.URL;
import java.text.DateFormat;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class ArtifactDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    static final DateFormat RELEASE_DATE_FORMAT = Dates.YYYY_MM_DD;

    public ArtifactDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    public Set<String> getUsedLangs() {
        return getText().keySet();
    }

    public DomainString getDisplayName() {
        DomainString displayName = (DomainString) getTag("name");
        return displayName;
    }

    public List<Author> getAuthors() {
        List<Author> authors = (List<Author>) getTag("author");
        return authors;
    }

    public Author getAuthor() {
        List<Author> authors = getAuthors();
        if (authors.isEmpty())
            return null;
        else
            return authors.get(0);
    }

    public IVersion getVersion() {
        IVersion version = (IVersion) getTag("version");
        return version;
    }

    public List<URL> getSiteLinks() {
        return null;
    }

    public URL getSiteLink() {
        List<URL> siteLinks = getSiteLinks();
        if (siteLinks.isEmpty())
            return null;
        else
            return siteLinks.get(0);
    }

}
