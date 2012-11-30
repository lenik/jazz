package net.bodz.bas.i18n.unit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;

public abstract class MeasureUnit
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String symbol;

    static Map<String, MeasureUnit> nameMap = new HashMap<String, MeasureUnit>();
    static Map<String, List<MeasureUnit>> symbolMap = new HashMap<String, List<MeasureUnit>>();

    public MeasureUnit(String name, String symbol) {
        if (name == null)
            throw new NullPointerException("name");
        if (symbol == null)
            throw new NullPointerException("symbol");
        this.name = name;
        this.symbol = symbol;

        if (nameMap.containsKey(name))
            throw new DuplicatedKeyException("Duplicated unit name: " + name);

        List<MeasureUnit> unitsWithSameSymbol = symbolMap.get(symbol);
        if (unitsWithSameSymbol == null) {
            unitsWithSameSymbol = new ArrayList<MeasureUnit>();
            symbolMap.put(symbol, unitsWithSameSymbol);
        }

        nameMap.put(name, this);
        unitsWithSameSymbol.add(this);
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public abstract boolean isStd();

    public abstract StdMeasureUnit getStdUnit();

    public abstract double getScale();

    public abstract double timesOf(MeasureUnit other);

    public abstract double timesOf(StdMeasureUnit other);

    public double timesOf(MeasureUnit other, IUnitConversionMap map) {
        double t = timesOf(other);
        if (t != Double.NaN)
            return t;

        t = map.convert(this.getStdUnit(), other.getStdUnit());
        if (t != Double.NaN)
            return t * this.getScale() / other.getScale();

        return Double.NaN;
    }

    public double timesOf(StdMeasureUnit other, IUnitConversionMap map) {
        double t = timesOf(other);
        if (t != Double.NaN)
            return t;

        t = map.convert(this.getStdUnit(), other);
        if (t != Double.NaN)
            return t * this.getScale();

        return Double.NaN;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static MeasureUnit forName(String name) {
        return nameMap.get(name);
    }

    /**
     * @return Non-<code>null</code> list of units with the specific symbol.
     */
    public static List<MeasureUnit> forSymbol(String symbol) {
        List<MeasureUnit> list = symbolMap.get(symbol);
        if (list == null)
            return Collections.emptyList();
        else
            return Collections.unmodifiableList(list);
    }

    public static MeasureUnit getUniqueUnitForSymbol(String symbol) {
        List<MeasureUnit> list = symbolMap.get(symbol);
        if (list == null || list.size() != 1)
            return null;
        else
            return list.get(0);
    }

}
