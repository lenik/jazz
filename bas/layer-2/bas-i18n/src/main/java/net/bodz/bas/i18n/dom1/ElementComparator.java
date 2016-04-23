package net.bodz.bas.i18n.dom1;

import java.text.Collator;
import java.util.Locale;

import net.bodz.bas.c.java.text.Collators;
import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ElementComparator
        extends AbstractNonNullComparator<IElement> {

    public static final int PRIORITY = 1;
    public static final int DETAIL_LEVEL = 2;
    public static final int NAME = 3;
    public static final int LABEL_OR_NAME = 4;
    public static final int LABEL = 5;
    public static final int IDENTITY = 6;

    private int[] fields;

    private Collator collator;

    public ElementComparator(int... fields) {
        this.fields = fields;
    }

    public Collator getCollator() {
        return collator;
    }

    /**
     * Set to <code>null</code> to use the collator for the current locale.
     * 
     * Use {@link Locale#CHINESE} for pinyin order.
     */
    public void setCollator(Collator collator) {
        this.collator = collator;
    }

    @Override
    public int compareNonNull(IElement o1, IElement o2) {
        int cmp;

        for (int field : fields) {
            switch (field) {
            case PRIORITY:
                int priority1 = o1.getPriority();
                int priority2 = o2.getPriority();
                cmp = priority1 - priority2;
                if (cmp != 0)
                    return cmp;
                break;

            case DETAIL_LEVEL:
                int level1 = o1.getDetailLevel();
                int level2 = o2.getDetailLevel();
                cmp = level1 - level2;
                if (cmp != 0)
                    return cmp;
                break;

            case NAME:
                String name1 = o1.getName();
                String name2 = o2.getName();
                cmp = Nullables.compare(name1, name2);
                if (cmp != 0)
                    return cmp;
                break;

            case LABEL:
                String label1 = Nullables.toString(o1.getLabel());
                String label2 = Nullables.toString(o2.getLabel());
                cmp = Nullables.compare(label1, label2);
                if (cmp != 0)
                    return cmp;
                break;

            case LABEL_OR_NAME:
                String s1 = labelOrName(o1);
                String s2 = labelOrName(o2);
                cmp = Collators.compare(collator, s1, s2);
                if (cmp != 0)
                    return cmp;
                break;

            case IDENTITY:
                int id1 = System.identityHashCode(o1);
                int id2 = System.identityHashCode(o2);
                cmp = id1 - id2;
                if (cmp != 0)
                    return cmp;
                break;
            }
        }

        return o1.equals(o2) ? 0 : -1;
    }

    protected String labelOrName(IElement element) {
        iString label = element.getLabel();
        if (label != null) {
            String s = label.toString();
            if (s != null)
                return s;
        }
        String name = element.getName();
        if (name != null)
            return name;
        return null;
    }

    public static final ElementComparator PrLvNmId = new ElementComparator(//
            PRIORITY, DETAIL_LEVEL, NAME, IDENTITY);

    public static final ElementComparator PrLvLnId = new ElementComparator(//
            PRIORITY, DETAIL_LEVEL, LABEL_OR_NAME, IDENTITY);

    public static final ElementComparator LnPrLvId = new ElementComparator(//
            LABEL_OR_NAME, PRIORITY, DETAIL_LEVEL, IDENTITY);

    public static final ElementComparator C = PrLvNmId;
    public static final ElementComparator LOCALE = PrLvLnId;

}
