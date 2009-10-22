package net.bodz.inplas.kid;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.Iterator;

import net.bodz.bas.io.Files;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TDParser {

    Object  xmlsrc;

    Project project;

    public TDParser(URL url) throws IOException {
        this.xmlsrc = url;
    }

    public TDParser(String file) throws IOException {
        this.xmlsrc = Files.canoniOf(file);
    }

    public TDParser(File file) throws IOException {
        this.xmlsrc = file;
    }

    @SuppressWarnings("unchecked")
    public Project parse() throws IOException, DocumentException {
        SAXReader reader = new SAXReader();
        Document document = null;
        if (xmlsrc instanceof File)
            document = reader.read((File) xmlsrc);
        else if (xmlsrc instanceof URL)
            document = reader.read((URL) xmlsrc);
        else if (xmlsrc instanceof Reader)
            document = reader.read((Reader) xmlsrc);
        else
            throw new UnexpectedException("Invalid configFile type");

        project = new Project();

        Element elmSection = document.getRootElement();
        Iterator<Element> itTables = elmSection.elementIterator("Table");
        while (itTables.hasNext()) {
            Element elmTable = itTables.next();
            String name = elmTable.elementTextTrim("Name");
            String comment = elmTable.elementTextTrim("Comment");

            Table table = new Table();
            table.setName(name);
            table.setComment(comment);
            project.addTable(table);

            Iterator<Element> itFields = elmTable.elementIterator("Field");
            while (itFields.hasNext()) {
                Element elmField = itFields.next();
                String name_X = elmField.elementTextTrim("Name");
                String display_X = elmField.elementTextTrim("Tip");
                String type_X = elmField.elementTextTrim("Type");
                String props_X = elmField.elementTextTrim("Props");
                String comment_X = elmField.elementTextTrim("Description");

                Field field = new Field();
                field.setName_X(name_X);
                field.setDisplay_X(display_X);
                field.setType_X(type_X);
                field.setProps_X(props_X);
                field.setComment_X(comment_X);
                table.addField(field);
            }
        }

        return project;
    }
}
