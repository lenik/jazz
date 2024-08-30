package net.bodz.mda.xjdoc.model;

public class DocTagEntry {

    public String name;
    public String extension;
    public String text;

    public DocTagEntry() {
    }

    public DocTagEntry(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public DocTagEntry(String name, String extension, String text) {
        this.name = name;
        this.extension = extension;
        this.text = text;
    }

}
