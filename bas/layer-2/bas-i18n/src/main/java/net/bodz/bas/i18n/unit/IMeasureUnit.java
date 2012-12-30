package net.bodz.bas.i18n.unit;

import java.io.Serializable;

import net.bodz.bas.i18n.dom.iString;

public interface IMeasureUnit
        extends Serializable {

    String getName();

    iString getLabel();

    String getSymbol();

    double in(IMeasureUnit other);

    double per(IMeasureUnit other);

}
