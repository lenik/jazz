package net.bodz.bas.site.vhost;

import java.util.List;

import net.bodz.bas.i18n.dom1.IElement;

public interface IVirtualHost
        extends IElement {

    List<HostSpecifier> getHostSpecifiers();

    Iterable<String> getParameterNames();

    String getParameter(String name);

    void setParameter(String name, String value);

    void removeParameter(String name);

    Iterable<String> getAttributeNames();

    <T> T getAttribute(String name);

    <T> T getAttribute(String name, T defaultValue);

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

}
