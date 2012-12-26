package net.bodz.bas.i18n.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.err.DuplicatedKeyException;

public class MeasureUnits {

    static Map<String, IMeasureUnit> nameMap = new HashMap<>();
    static Map<String, List<IMeasureUnit>> symbolMap = new HashMap<>();

    public static synchronized void register(IMeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");

        String name = unit.getName();
        IMeasureUnit existing = nameMap.get(name);
        if (existing != null)
            throw new DuplicatedKeyException(String.format("Measure unit name is in use: %s", name));

        String symbol = unit.getSymbol();
        List<IMeasureUnit> symbolUnits = forSymbol(symbol);

        nameMap.put(name, unit);
        symbolUnits.add(unit);
    }

    /**
     * Get the measure unit by name.
     * 
     * @param name
     *            The measure unit name to get.
     * @return The measure unit.
     * @throws NoSuchElementException
     *             If the measure name isn't defined.
     */
    public static IMeasureUnit forName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        IMeasureUnit unit = nameMap.get(name);
        if (unit == null)
            throw new NoSuchElementException("unit name: " + name);
        return unit;
    }

    /**
     * Get the measure unit with the specific symbol.
     * 
     * @return <code>null</code> if the unit for the symbol isn't defined.
     */
    public static List<IMeasureUnit> forSymbol(String symbol) {
        if (symbol == null)
            throw new NullPointerException("symbol");

        List<IMeasureUnit> unitList = symbolMap.get(symbol);
        if (unitList == null)
            symbolMap.put(symbol, unitList = new ArrayList<>());

        return unitList;
    }

}
