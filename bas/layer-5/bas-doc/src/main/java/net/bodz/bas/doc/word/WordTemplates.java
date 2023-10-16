package net.bodz.bas.doc.word;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class WordTemplates {

    public static final String NORMAL = "Normal.dotx";

    public static XWPFDocument createNormal() {
        return createFromTemplate(NORMAL);
    }

    public static XWPFDocument createFromTemplate(String name) {
        URL resource = WordTemplates.class.getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("bad template name: " + name);
        }
        return createFromTemplate(resource);
    }

    public static XWPFDocument createFromTemplate(URL resource) {
        try (InputStream in = resource.openStream()) {
            XWPFDocument document = new XWPFDocument(in);
            return document;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
