package net.bodz.bas.meta.build;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.mda.xjdoc.model.DecoratedClassDoc;
import net.bodz.mda.xjdoc.model.IClassDoc;
import net.bodz.mda.xjdoc.util.Author;

public class AppClassDoc
        extends DecoratedClassDoc {

    private static final long serialVersionUID = 1L;

    public AppClassDoc(IClassDoc _orig) {
        super(_orig);
    }

    public Author getAuthor() {
        Author author = (Author) getTag("author");
        return author;
    }

    public IVersion getVersion() {
        IVersion version = (IVersion) getTag("version");
        return version;
    }

    public DomainString getLabel() {
        DomainString label = (DomainString) getTag("label");
        return label;
    }

    public String getTextHeader() {
        String header = getText().toString();
        header = header.trim();
        int newline = header.indexOf('\n');
        if (newline != -1)
            header = header.substring(0, newline).trim();
        return header;
    }

    public String getTextBody() {
        String body = getText().toString();
        body = body.trim();
        int newline = body.indexOf('\n');
        if (newline != -1)
            body = body.substring(newline + 1).trim();
        return body;
    }

    public ReleaseDescription getReleaseDescription() {
        ReleaseDescription release = new ReleaseDescription();

        DomainString label = getLabel();
        if (label != null)
            release.setName(label.toString());

        release.setDescription(getText().toString());

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

}
