package net.bodz.mda.xjdoc.meta;

import java.util.ArrayList;
import java.util.List;

public abstract class IndexedTagType
        extends AbstractTagType {

    @Override
    public Object parse(Object cont, String string) {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) cont;
        if (list == null)
            list = new ArrayList<Object>();

        String indexText = WordTokenizer.firstWord(string);
        String valueText = string.substring(indexText.length());
        indexText = indexText.trim();
        valueText = valueText.trim();

        int index = parseIndex(indexText);
        while (list.size() < index)
            list.add(null);

        Object valueCont = list.get(index);
        Object value = parseValue(valueCont, valueText);
        if (valueCont == null)
            list.set(index, value);
        return list;
    }

    @Override
    public String[] format(Object value) {
        List<?> list = (List<?>) value;
        String[] array = new String[list.size()];
        for (int index = 0; index < array.length; index++) {
            Object item = list.get(index);
            // String indexText = formatIndex(index);
            String valueText = formatValue(item);
            array[index] = valueText;
        }
        return array;
    }

    protected int parseIndex(String indexText) {
        int index = Integer.parseInt(indexText, 0);
        return index;
    }

    protected String formatIndex(int index) {
        return String.valueOf(index);
    }

    protected abstract Object parseValue(Object cont, String valueString);

    protected abstract String formatValue(Object value);

}
