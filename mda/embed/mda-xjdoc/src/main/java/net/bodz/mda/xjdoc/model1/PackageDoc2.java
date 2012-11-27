package net.bodz.mda.xjdoc.model1;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.mda.xjdoc.model.DecoratedJavaElementDoc;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public class PackageDoc2
        extends ArtifactDoc {

    private static final long serialVersionUID = 1L;

    public PackageDoc2(IJavaElementDoc _orig) {
        super(_orig);
    }

    public PackageDoc2(DecoratedJavaElementDoc _orig) {
        super(_orig.getWrapped());
    }

    public Set<String> getLangs() {
        Set<String> langs = new TreeSet<String>();
        // getTag("lang")...
        return langs;
    }

    public ReleaseDescription getReleaseDescription() {
        ReleaseDescription release = new ReleaseDescription();

        DomainString displayName = getDisplayName();
        if (displayName != null)
            release.setName(displayName.toString());

        DomainString text = getText();
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

    public String getReleaseDateString() {
        Date releaseDate = getReleaseDate();
        if (releaseDate == null)
            return "recently";
        else
            return RELEASE_DATE_FORMAT.format(releaseDate);
    }

}
