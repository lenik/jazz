package net.bodz.bas.fmt.xml;

import java.util.List;

import javax.xml.namespace.QName;

import net.bodz.bas.fmt.api.ITextAttribute;

public interface IXmlElement {

    QName getName();

    List<ITextAttribute> getAttributes();

    List<IXmlElement> getChildren();

}
