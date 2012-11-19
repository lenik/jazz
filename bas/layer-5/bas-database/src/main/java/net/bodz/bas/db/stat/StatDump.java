package net.bodz.bas.db.stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StatDump {

    boolean treeMode;
    Map<String, ICounterDef<?>> usedCounterDefs = new LinkedHashMap<>();
    Map<String, Integer> counterIndex = new HashMap<>();

    int fieldCount;
    List<StatDumpLine> lines = new ArrayList<StatDumpLine>();

    public StatDump(boolean treeMode, StatNode root) {
        this.treeMode = treeMode;

        scanUsedCounters(root);
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

            int nextIndex = counterIndex.size();
            counterIndex.put(name, nextIndex);
        }

        for (StatNode child : node.getChildMap().values())
            scanUsedCounters(child);
    }

    void scan(String prefix, String nodeName, StatNode node) {
        StatDumpField[] fields = new StatDumpField[this.fieldCount];

        for (ICounter<?> counter : node.getCounters()) {
            String counterName = counter.getName();

            StatDumpField field = new StatDumpField();
            field.def = usedCounterDefs.get(counterName);
            field.value = counter.getValue().toString();

            int index = counterIndex.get(counterName);
            fields[index] = field;
        }

        StatDumpLine line = new StatDumpLine(prefix, nodeName, fields);
        lines.add(line);

        if (treeMode) {
            List<Entry<String, StatNode>> entries = new ArrayList<>(node.getChildMap().entrySet());
            int entryCount = entries.size();

            if (!prefix.isEmpty()) {
                boolean lastParent = prefix.endsWith(" `- ");
                prefix = prefix.substring(0, prefix.length() - 4);
                prefix += lastParent ? "    " : " |  ";
            }

            for (int i = 0; i < entryCount; i++) {
                String childPrefix = i == entryCount - 1 ? " `- " : " |- ";

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
