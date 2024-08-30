package net.bodz.mda.xjdoc.model.artifact;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.build.IVersion;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.t.coll.IContainer;
import net.bodz.mda.xjdoc.model.DecoratedMutableElementDoc;
import net.bodz.mda.xjdoc.model.IMutableElementDoc;
import net.bodz.mda.xjdoc.model.javadoc.Author;

public class ArtifactDoc
        extends DecoratedMutableElementDoc {

    private static final long serialVersionUID = 1L;

    public ArtifactDoc(IMutableElementDoc _orig) {
        super(_orig);
    }

    public iString getLabel() {
        return super.getText(LABEL);
    }

    public iString getDescription() {
        return super.getText(DESCRIPTION);
    }

    public Set<String> getUsedLangs() {
        Set<String> langs = new LinkedHashSet<String>();

        for (String domain : getText().keySet())
            if (domain != null)
                langs.add(domain);

        String[] langTags = getTag("lang").getStringArray();
        if (langTags != null)
            for (String langTag : langTags)
                langs.add(langTag);

        return langs;
    }

    public List<Author> getAuthors() {
        @SuppressWarnings("unchecked")
        IContainer<Author> authors = (IContainer<Author>) getTag("author").getContainer();
        if (authors == null)
            return Collections.emptyList();
        else
            return authors.toList();
    }

    public Author getAuthor() {
        List<Author> authors = getAuthors();
        if (authors.isEmpty())
            return null;
        else
            return authors.get(0);
    }

    public IVersion getVersion() {
        IVersion version = (IVersion) getTag("version").getData();
        return version;
    }

    public List<URL> getSiteLinks() {
        return Collections.emptyList();
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

    public LocalDate getReleaseDate() {
        // getTag("release-date");
        return null;
    }

    static final DateTimeFormatter RELEASE_DATE_FORMAT = DateTimes.ISO_LOCAL_DATE;

    public String getReleaseDateString() {
        LocalDate releaseDate = getReleaseDate();
        if (releaseDate == null)
            return "recently";
        else
            return RELEASE_DATE_FORMAT.format(releaseDate);
    }

}
