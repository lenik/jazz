package net.bodz.lily.gen;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ImportedTypes
        extends TreeSet<Class<?>> {

    private static final long serialVersionUID = 1L;

    final QOrder order;

    public ImportedTypes() {
        this(QOrder.INSTANCE);
    }

    public ImportedTypes(List<String> order) {
        this(new QOrder(new PackageOrder(order)));
    }

    public ImportedTypes(QOrder order) {
        super(order);
        this.order = order;
    }

    public Class<?> ref(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        add(type);
        return type;
    }

    public String a(Class<?> type) {
        ref(type);
        return "@" + type.getSimpleName();
    }

    public String name(Class<?> type) {
        ref(type);
        return type.getName();
    }

    public String simple(Class<?> type) {
        ref(type);
        return type.getSimpleName();
    }

    public void dump(IPrintOut out) {
        PackageOrderList orderList = order.getPackageOrder().getOrderList();

        int lastSector = -1;
        for (Class<?> c : this) {
            Package pkg = c.getPackage();
            if (pkg == null)
                continue;
            if ("java.lang".equals(pkg.getName()))
                continue;

            String qPkg = pkg.getName();
            int sector = orderList.find(qPkg);

            if (lastSector != -1 && lastSector != sector)
                out.println();

            out.println("import " + c.getName() + ";");
            lastSector = sector;
        }
    }

}

class QOrder
        extends AbstractNonNullComparator<Class<?>> {

    PackageOrder packageOrder;

    public QOrder(PackageOrder packageOrder) {
        this.packageOrder = packageOrder;
    }

    public PackageOrder getPackageOrder() {
        return packageOrder;
    }

    @Override
    public int compareNonNull(Class<?> o1, Class<?> o2) {
        Package p1 = o1.getPackage();
        Package p2 = o2.getPackage();
        int cmp = PackageOrder.INSTANCE.compare(p1, p2);
        if (cmp != 0)
            return cmp;

        cmp = o1.getSimpleName().compareTo(o2.getSimpleName());
        return cmp;
    }

    public static final QOrder INSTANCE = new QOrder(PackageOrder.INSTANCE);

}

class PackageOrder
        extends AbstractNonNullComparator<Package> {

    PackageOrderList orderList;

    public PackageOrder(List<String> order) {
        this.orderList = new PackageOrderList(order);
    }

    public PackageOrderList getOrderList() {
        return orderList;
    }

    @Override
    public int compareNonNull(Package o1, Package o2) {
        String n1 = o1.getName();
        String n2 = o2.getName();
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

    public static final PackageOrder INSTANCE = new PackageOrder(DEFAULT_ORDER);

}