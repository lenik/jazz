package net.bodz.bas.codegen;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ImportSet
        extends TreeSet<String> {

    private static final long serialVersionUID = 1L;

    final QNameOrder order;

    public ImportSet() {
        this(QNameOrder.INSTANCE);
    }

    public ImportSet(List<String> order) {
        this(new QNameOrder(new PackageNameOrder(order)));
    }

    public ImportSet(QNameOrder order) {
        super(order);
        this.order = order;
    }

    public Class<?> ref(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        add(clazz.getName());
        return clazz;
    }

    public QualifiedName ref(QualifiedName qName) {
        if (qName == null)
            throw new NullPointerException("qName");
        add(qName.getFullName());
        return qName;
    }

    public String ref(String className) {
        if (className == null)
            throw new NullPointerException("className");
        add(className);
        return className;
    }

    public String a(Class<?> type) {
        return "@" + ref(type).getSimpleName();
    }

    /**
     * @return canonical name
     */
    public String c(String fullName) {
        return ref(fullName);
    }

    /**
     * @return canonical name
     */
    public String c(Class<?> type) {
        ref(type);
        return type.getCanonicalName();
    }

    public String name(String typename) {
        return StringPart.afterLast(ref(typename), ".");
    }

    public String name(Class<?> type) {
        return ref(type).getSimpleName();
    }

    public String name(QualifiedName type) {
        return ref(type).name;
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