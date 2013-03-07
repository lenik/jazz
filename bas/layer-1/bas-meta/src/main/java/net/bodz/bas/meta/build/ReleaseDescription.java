package net.bodz.bas.meta.build;

import java.util.Date;

/**
 * You can get a release description by parse the RCS-ID from the {@link RcsKeywords} annotation.
 * 
 * @see RcsKeywordsUtil
 */
public class ReleaseDescription
        implements Comparable<ReleaseDescription> {

    String name;
    String description;
    String author;
    IVersion version;
    Date releaseDate;
    String releaseNotes;
    String copyright;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public IVersion getVersion() {
        return version;
    }

    public void setVersion(IVersion version) {
        this.version = version;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDateString() {
        if (releaseDate == null)
            return null;
        else
            return RcsDates.formatDate(releaseDate.getTime());
    }

    public String getReleaseTimeString() {
        if (releaseDate == null)
            return null;
        else
            return RcsDates.formatTime(releaseDate.getTime());
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get canonical release id in format: name_version.
     */
    public String getCanonicalId() {
        return name + "_" + version;
    }

    /**
     * Get friendly release id in format: name-version.
     */
    public String getFriendlyId() {
        return name + "-" + version;
    }

    /**
     * @see #getFriendlyId()
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(getFriendlyId());
        if (description != null) {
            buf.append(": ");
            buf.append(description);
        }
        return buf.toString();
    }

    @Override
    public int compareTo(ReleaseDescription o) {
        if (o == null)
            return 1;
        int cmp = name.compareTo(o.name);
        if (cmp != 0)
            return cmp;

        cmp = version.compareTo(o.version);
        if (cmp != 0)
            return cmp;

        return 0;
    }

}
