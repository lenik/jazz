package net.bodz.lily.entity.format;

import java.io.ByteArrayOutputStream;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.repr.content.FileContent;
import net.bodz.bas.std.rfc.mime.ContentTypes;
import net.bodz.bas.t.tuple.Split;

public class WordUtils {

    public static FileContent toFile(WordprocessingMLPackage wordDoc, String defaultFileName)
            throws Docx4JException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(30000);
        wordDoc.save(buf);

        String fileName = defaultFileName;
        String title = wordDoc.getTitle();
        if (title != null && !title.trim().isEmpty()) {
            Split name_ext = Split.nameExtension(defaultFileName);
            String extension = name_ext.b;
            fileName = String.format("%s.%s", title.trim(), extension);
        }
        return new FileContent(fileName, ContentTypes.application_vnd_ms_excel, buf.toByteArray());
    }

}
