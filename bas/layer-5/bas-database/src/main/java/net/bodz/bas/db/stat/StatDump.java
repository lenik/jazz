package net.bodz.bas.db.stat;

import java.util.*;
import java.util.Map.Entry;

import net.bodz.bas.c.string.TreeLineChars;

public class StatDump {

    StatFormatter formatter;

    Map<String, ICounterDef<?>> usedCounterDefs = new LinkedHashMap<>();
    Map<String, Integer> counterIndex = new HashMap<>();

    int fieldCount;
    List<StatDumpLine> lines = new ArrayList<StatDumpLine>();

    public StatDump(StatNode root, StatFormatter formatter) {
        this.formatter = formatter;

        scanUsedCounters(root);

        if (formatter.removeZeroColumns) {
            Set<String> zeroCounters = new HashSet<String>(usedCounterDefs.keySet());
            removeNonZeroCounters(zeroCounters, root);
            for (String zeroCounter : zeroCounters)
                usedCounterDefs.remove(zeroCounter);
        }

        // Create index map
        for (String name : usedCounterDefs.keySet()) {
            int nextIndex = counterIndex.size();
            counterIndex.put(name, nextIndex);
        }
        fieldCount = counterIndex.size();

        scan("", "", root);
    }

    void scanUsedCounters(StatNode node) {
        for (ICounter<?> counter : node.getCounters()) {
            String name = counter.getName();

            ICounterDef<?> existedDef = usedCounterDefs.get(name);
            if (existedDef != null)
                // Check if the existed def is equals to this one.
                continue;

            ICounterDef<?> def = counter.getDefinition();
            usedCounterDefs.put(name, def);
        }

        for (StatNode child : node.getChildMap().values())
            scanUsedCounters(child);
    }

    void removeNonZeroCounters(Set<String> names, StatNode node) {
        for (ICounter<?> counter : node.getCounters()) {
            String name = counter.getName();
            Number value = counter.getValue();

            ICounterDef<Number> def = (ICounterDef<Number>) counter.getDefinition();
            boolean zero = def.appxEquals(def.getZero(), value);
            if (!zero)
                names.remove(name);
        }

        for (StatNode child : node.getChildMap().values())
            removeNonZeroCounters(names, child);
    }

    void scan(String prefix, String nodeName, StatNode node) {
        StatDumpField[] fields = new StatDumpField[this.fieldCount];

        for (ICounter<?> counter : node.getCounters()) {
            String counterName = counter.getName();
            Integer fieldIndex = counterIndex.get(counterName);
            if (fieldIndex == null)
                continue;

            ICounterDef<Number> def = (ICounterDef<Number>) usedCounterDefs.get(counterName);
            Number value = counter.getValue();

            StatDumpField field = new StatDumpField();
            field.def = def;

            if (formatter.isConvertZeroToSpace()) {
                boolean zero = def.appxEquals(def.getZero(), value);
                if (zero)
                    field.value = "";
                else
                    field.value = value.toString();
            } else {
                field.value = value.toString();
            }

            fields[fieldIndex] = field;
        }

        StatDumpLine line = new StatDumpLine(prefix, nodeName, fields);
        lines.add(line);

        if (formatter.isTreeGraph()) {
            TreeLineChars treeLineChars = formatter.getTreeLineChars();

            List<Entry<String, StatNode>> entries = new ArrayList<>(node.getChildMap().entrySet());
            int entryCount = entries.size();

            if (!prefix.isEmpty()) {
                boolean lastParent = prefix.endsWith(treeLineChars.treeLastBranch);
                prefix = prefix.substring(0, prefix.length() - 4);
                prefix += lastParent ? treeLineChars.treeSkip : treeLineChars.treeLine;
            }

            for (int i = 0; i < entryCount; i++) {
                String childPrefix = i == entryCount - 1 ? treeLineChars.treeLastBranch : treeLineChars.treeBranch;

                Entry<String, StatNode> entry = entries.get(i);
                String childNodeName = entry.getKey();
                StatNode childNode = entry.getValue();
                scan(prefix + childPrefix, childNodeName, childNode);
            }

        } else {
            if (prefix.isEmpty())
                prefix = nodeName;
            else
                prefix += "/" + nodeName;
            for (Entry<String, StatNode> entry : node.getChildMap().entrySet()) {
                String childNodeName = entry.getKey();
                StatNode childNode = entry.getValue();
                scan(prefix, childNodeName, childNode);
            }
        }
    }

    public int getPrefixNameWidth() {
        int width = 0;
        for (StatDumpLine line : lines)
            width = Math.max(width, line.prefix.length() + line.name.length());
        return width;
    }

    public int[] getFieldWidths(boolean withTitle) {
        int widths[] = new int[fieldCount];
        if (withTitle) {
            int fieldIndex = 0;
            for (Entry<String, ICounterDef<?>> entry : usedCounterDefs.entrySet()) {
                String name = entry.getKey();

                // if (withUnits)
                // ICounterDef<?> def = entry.getValue();
                // String unit = def.getUnit();

                widths[fieldIndex] = name.length();
                fieldIndex++;
            }
        }

        for (StatDumpLine line : lines) {
            for (int i = 0; i < line.fields.length; i++) {
                StatDumpField field = line.fields[i];
                if (field == null)
                    continue;
                int width = field.value.length();
                widths[i] = Math.max(widths[i], width);
            }
        }

        return widths;
    }

}

class StatDumpLine {
    String prefix;
    String name;
    StatDumpField[] fields;

    public StatDumpLine(String prefix, String name, StatDumpField[] fields) {
        this.prefix = prefix;
        this.name = name;
        this.fields = fields;
    }
}

class StatDumpField {
    ICounterDef<?> def;
    String value;
}
