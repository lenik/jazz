package net.bodz.bas.i18n.unit;

import net.bodz.bas.i18n.dom.iString;

public abstract class AbstractMeasureUnit
        implements IMeasureUnit {

    private static final long serialVersionUID = 1L;

    private String name;
    private String symbol;

    public AbstractMeasureUnit(String name, String symbol) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
        this.symbol = symbol;

        MeasureUnits.register(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public iString getLabel() {
        return null;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return name;
    }

}
