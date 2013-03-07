package net.bodz.mda.xjdoc.model.artifact;

import java.net.URL;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.Author;

public class ArtifactDoc
        extends DecoratedJavaElementDoc {

    private static final long serialVersionUID = 1L;

    public ArtifactDoc(IJavaElementDoc _orig) {
        super(_orig);
    }

    public Set<String> getUsedLangs() {
        return getText().keySet();
    }

    public List<Author> getAuthors() {
        List<Author> authors = (List<Author>) getTag("author");
        if (authors == null)
            authors = Collections.emptyList();
        else
            authors = Collections.unmodifiableList(authors);
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

    // optional...

    public Set<String> getLangs() {
        Set<String> langs = new TreeSet<String>();
        // getTag("lang")...
        return langs;
    }

    public ReleaseDescription getReleaseDescription() {
        ReleaseDescription release = new ReleaseDescription();

        iString label = getLabel();
        if (label != null)
            release.setName(label.toString());

        iString text = getText();
        if (text != null)
            release.setDescription(text.toString());

        release.setVersion(getVersion());

        Author author = getAuthor();
        if (author != null) {
            release.setAuthor(author.getName().toString());
        }

        Object copyright = getTag("copyright");
        if (copyright != null)
            release.setCopyright(copyright.toString());

        // String releaseNotes = (String) getTag("release.notes");
        // release.setReleaseNotes(releaseNotes);

        return release;
    }

    public Date getReleaseDate() {
        // getTag("release-date");
        return null;
    }

    static final DateFormat RELEASE_DATE_FORMAT = Dates.YYYY_MM_DD;

    public String getReleaseDateString() {
        Date releaseDate = getReleaseDate();
        if (releaseDate == null)
            return "recently";
        else
            return RELEASE_DATE_FORMAT.format(releaseDate);
    }

}
