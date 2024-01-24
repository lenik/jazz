package net.bodz.lily.format.doc;

import java.io.ByteArrayOutputStream;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IWordForm {

    void readObject(WordprocessingMLPackage pack)
            throws ParseException, LoaderException;

    void writeObject(WordprocessingMLPackage pack)
            throws FormatException;

    default WordprocessingMLPackage toWordPackage()
            throws FormatException {
        WordprocessingMLPackage pack;
        try {
            pack = WordprocessingMLPackage.createPackage();
        } catch (InvalidFormatException e) {
            throw new FormatException(e.getMessage(), e);
        }
        writeObject(pack);
        return pack;
    }

    default byte[] toWordBytes()
            throws FormatException {
        WordprocessingMLPackage wordPackage = toWordPackage();
        ByteArrayOutputStream buf = new ByteArrayOutputStream(30000);
        try {
            wordPackage.save(buf);
        } catch (Docx4JException e) {
            throw new FormatException("failed to convert bytes: " + e.getMessage(), e);
        }
        byte[] bin = buf.toByteArray();
        return bin;
    }

}
