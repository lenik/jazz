package net.bodz.bas.i18n.unit;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.DomainString;

public interface IMeasureUnit
        extends Serializable {

    String getName();

    DomainString getLabel();

    String getSymbol();

    double in(IMeasureUnit other);

    double per(IMeasureUnit other);

}
