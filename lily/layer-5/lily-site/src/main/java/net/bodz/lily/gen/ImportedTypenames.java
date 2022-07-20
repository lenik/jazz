package net.bodz.lily.gen;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ImportedTypenames
        extends TreeSet<String> {

    private static final long serialVersionUID = 1L;

    final QNameOrder order;

    public ImportedTypenames() {
        this(QNameOrder.INSTANCE);
    }

    public ImportedTypenames(List<String> order) {
        this(new QNameOrder(new PackageNameOrder(order)));
    }

    public ImportedTypenames(QNameOrder order) {
        super(order);
        this.order = order;
    }

    public Class<?> ref(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        add(type.getName());
        return type;
    }

    public String ref(String type) {
        if (type == null)
            throw new NullPointerException("type");
        add(type);
        return type;
    }

    public String a(Class<?> type) {
        ref(type);
        return "@" + type.getSimpleName();
    }

    public String name(String typename) {
        ref(typename);
        return typename;
    }

    public String name(Class<?> type) {
        ref(type);
        return type.getName();
    }

    public String simple(String typename) {
        ref(typename);
        return StringPart.afterLast(typename, ".");
    }

    public String simple(Class<?> type) {
        ref(type);
        return type.getSimpleName();
    }

    public void dump(IPrintOut out) {
        PackageOrderList orderList = order.getPackageOrder().getOrderList();

        int lastSector = -1;
        for (String c : this) {
            String pkg = StringPart.beforeLast(c, ".");
            if (pkg == null)
                continue;
            if ("java.lang".equals(pkg))
                continue;

            int sector = orderList.find(pkg);

            if (lastSector != -1 && lastSector != sector)
                out.println();

            out.println("import " + c + ";");
            lastSector = sector;
        }
    }

}

class QNameOrder
        extends AbstractNonNullComparator<String> {

    PackageNameOrder packageOrder;

    public QNameOrder(PackageNameOrder packageOrder) {
        this.packageOrder = packageOrder;
    }

    public PackageNameOrder getPackageOrder() {
        return packageOrder;
    }

    @Override
    public int compareNonNull(String o1, String o2) {
        String p1 = StringPart.beforeLast(o1, ".");
        String p2 = StringPart.beforeLast(o2, ".");
        int cmp = PackageNameOrder.INSTANCE.compare(p1, p2);
        if (cmp != 0)
            return cmp;

        String n1 = StringPart.afterLast(o1, ".");
        String n2 = StringPart.afterLast(o2, ".");
        cmp = n1.compareTo(n2);
        return cmp;
    }

    public static final QNameOrder INSTANCE = new QNameOrder(PackageNameOrder.INSTANCE);

}

class PackageNameOrder
        extends AbstractNonNullComparator<String> {

    PackageOrderList orderList;

    public PackageNameOrder(List<String> order) {
        this.orderList = new PackageOrderList(order);
    }

    public PackageOrderList getOrderList() {
        return orderList;
    }

    @Override
    public int compareNonNull(String n1, String n2) {
        int i1 = orderList.find(n1);
        int i2 = orderList.find(n2);
        int cmp = i1 - i2;
        if (cmp != 0)
            return cmp;
        cmp = n1.compareTo(n2);
        return cmp;
    }

    public static List<String> DEFAULT_ORDER = Arrays.asList(//
            "java", //
            "javax", //
            "sun", //
            "org", //
            "net", //
            "com", //
            "user");

    public static final PackageNameOrder INSTANCE = new PackageNameOrder(DEFAULT_ORDER);

}