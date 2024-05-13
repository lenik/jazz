package net.bodz.bas.doc.word.xwpf;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.Enum;

import net.bodz.bas.doc.word.StyleIds;
import net.bodz.bas.doc.word.Styles;
import net.bodz.bas.doc.word.XwNumbering;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.variant.IVariant;

public class XwDocument
        extends AbstractXwNode
        implements
            IXwHavePars {

    XWPFDocument _document;

    public final XwNumbering numbering;
    public final Styles styles = new Styles();

    public XwDocument(XWPFDocument element) {
        this(null, element);
    }

    public XwDocument(IXwNode parent, XWPFDocument _document) {
        super(parent);
        this._document = _document;

        numbering = new XwNumbering(_document);

        XWPFStyles _styles = _document.getStyles();
        try {
            CTStyles ctStyles = _document.getStyle();
            for (CTStyle ctStyle : ctStyles.getStyleList()) {
                String id = ctStyle.getStyleId();
                XWPFStyle _style = _styles.getStyle(id);

                Enum type = _style.getType();
                StyleIds styleIds = styles.getStyleIds(type.toString());

                String name = _style.getName();
                styleIds.add(name, id);
            }
        } catch (XmlException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public XwDocument getDocument() {
        return this;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.DOCUMENT;
    }

    @Override
    public XWPFDocument getElement() {
        return _document;
    }

    static final String TRACK_REVISIONS = "trackRevisions";

    @Override
    public boolean attribute(String name, IVariant value) {
        switch (name) {
        case TRACK_REVISIONS:
            _document.setTrackRevisions(value.getBoolean());
            return true;
        }
        return false;
    }

    @Override
    public XwPar addPar() {
        XWPFParagraph _par = _document.createParagraph();
        return new XwPar(this, _par);
    }

    @Override
    public XwPar getParToAppend() {
        List<XWPFParagraph> pars = _document.getParagraphs();
        if (pars.isEmpty())
            return addPar();
        XWPFParagraph lastPar = pars.get(pars.size() - 1);
        return new XwPar(this, lastPar);
    }

    @Override
    public void addPlainText(String text) {
        List<XWPFParagraph> pars = _document.getParagraphs();
        XWPFParagraph lastPar;
        if (pars.isEmpty())
            lastPar = _document.createParagraph();
        else
            lastPar = pars.get(pars.size() - 1);
        XWPFRun run = lastPar.createRun();
        run.setText(text);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        out.beginElement("document");

        for (XWPFParagraph _par : _document.getParagraphs()) {
            XwPar par = new XwPar(this, _par);
            par.writeObject(out);
        }

        out.endElement();
    }

}
