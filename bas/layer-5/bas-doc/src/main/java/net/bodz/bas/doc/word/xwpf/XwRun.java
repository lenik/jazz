package net.bodz.bas.doc.word.xwpf;

import javax.xml.stream.XMLStreamException;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd.Enum;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.xq.IElement;
import net.bodz.bas.t.variant.IVariant;

public class XwRun
        extends AbstractXwNode {

    XWPFRun element;

    public XwRun(XwPar parent, XWPFRun element) {
        super(parent);
        this.element = element;
    }

    @Override
    public XwNodeType getType() {
        return XwNodeType.RUN;
    }

    @Override
    public XwPar getParent() {
        return (XwPar) super.getParent();
    }

    @Override
    public XWPFRun getElement() {
        return element;
    }

    @Override
    public boolean attribute(String name, IVariant value) {
        // element.setColor(name);
        return super.attribute(name, value);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        CTR cTR = element.getCTR();
        out.beginElement("R");

        CTRPr rPr = cTR.getRPr();
        if (rPr != null) {
            out.beginElement("Pr");

            // for (CTFonts rFonts : rPr.getRFontsArray())

            for (CTShd cTShd : rPr.getShdArray()) {
                Enum val = cTShd.getVal();
                Object color = cTShd.getColor();
                Object fill = cTShd.getFill();
                out.beginElement("Shd");
                out.attributeNotNull("val", val);
                out.attributeNotNull("color", color);
                out.attributeNotNull("fill", fill);
                out.endElement();
            }
            for (CTHighlight highlight : rPr.getHighlightArray()) {
                out.beginElement("Highlight");
                STHighlightColor.Enum val = highlight.getVal();
                out.attributeNotNull("val", val);
                out.endElement();
            }
            out.endElement();
        } // CTRPr

        // cTR.getBrArray();

        for (CTText t : cTR.getTArray()) {
            out.element("T", t.getStringValue());
        }
        out.endElement();
    }

}
