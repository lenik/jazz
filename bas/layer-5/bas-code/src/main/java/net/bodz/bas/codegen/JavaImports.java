package net.bodz.bas.codegen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.t.order.AbstractNonNullComparator;
import net.bodz.bas.t.tuple.QualifiedName;

public class JavaImports
        extends TreeSet<String> {

    private static final long serialVersionUID = 1L;

    final QNameOrder order;
    Set<String> excludePackages = new HashSet<>();
    Set<String> excludeClassNames = new HashSet<>();

    public JavaImports(String packageName) {
        this(packageName, QNameOrder.INSTANCE);
    }

    public JavaImports(String packageName, List<String> order) {
        this(packageName, new QNameOrder(new PackageNameOrder(order)));
    }

    public JavaImports(String packageName, QNameOrder order) {
        super(order);
        this.order = order;

        excludePackages.add("java.lang");
        excludePackages.add(packageName);
    }

    public void excludePackage(String packageName) {
        excludePackages.add(packageName);
    }

    public void excludeClass(String className) {
        excludeClassNames.add(className);
    }

    public Class<?> ref(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (clazz.isPrimitive())
            return clazz;
        add(clazz.getCanonicalName());
        return clazz;
    }

    public QualifiedName ref(QualifiedName qName) {
        if (qName == null)
            throw new NullPointerException("qName");
        add(qName.getFullName());
        return qName;
    }

    public String ref(String genericType) {
        if (genericType == null)
            throw new NullPointerException("genericType");

        int pos = genericType.indexOf('<');
        String className;
        if (pos != -1) {
            className = genericType.substring(0, pos);
        } else {
            className = genericType;
        }

        add(className);
        return genericType;
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

    /**
     * Reference and return the simple name.
     */
    public String name(String genericType) {
        int pos = genericType.indexOf('<');
        String className;
        if (pos != -1) {
            className = genericType.substring(0, pos);
        } else {
            className = genericType;
        }

        add(className);

        pos = className.lastIndexOf('.');
        if (pos == -1)
            return className;
        else
            return className.substring(pos + 1);
    }

    public String name(Class<?> type) {
        return ref(type).getSimpleName();
    }

    public String name(QualifiedName type) {
        return ref(type).name;
    }

    public String simpleId(Enum<?> val) {
        return name(val.getClass()) + "." + val.name();
    }

    public int accept(JavaImportsVisitor visitor) {
        PackageOrderList orderList = order.getPackageOrder().getOrderList();

        int lastSector = -1;
        int count = 0;
        for (String className : this) {
            if (excludeClassNames.contains(className)) {
                count += visitor.item(className, true);
                continue;
            }

            String packageName = StringPart.beforeLast(className, ".");
            if (packageName == null)
                continue;
            if (excludePackages.contains(packageName)) {
                count += visitor.item(className, true);
                continue;
            }

            int sector = orderList.find(packageName);

            if (lastSector != -1 && lastSector != sector)
                count += visitor.separator();

            count += visitor.item(className, false);
            lastSector = sector;
        }
        return count;
    }

    public int dump(IPrintOut out) {
        return accept(new JavaImportsVisitor() {
            @Override
            public int item(String name, boolean excluded) {
                if (excluded) {
                    // out.println("// import " + name + ";");
                    return 0;
                }
                out.println("import " + name + ";");
                return 1;
            }

            @Override
            public int separator() {
                out.println();
                return 1;
            }
        });
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

        // it's ok if either p1 or p2 is null.
        int cmp = PackageNameOrder.INSTANCE.compare(p1, p2);
        if (cmp != 0)
            return cmp;

        String n1 = StringPart.afterLast(o1, ".", o1);
        String n2 = StringPart.afterLast(o2, ".", o1);
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