package net.bodz.lily.gen;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Table;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IType;

public class SqlMapperGenerator {

    static String TAB1 = "    ";
    static String TAB2 = "        ";
    static String TAB3 = "            ";

    public void convertDeclaredFields(Class<?> clazz) {

        IType type = PotatoTypes.getInstance().loadType(clazz);

        String pkg = clazz.getPackage().getName();
        String simple = clazz.getSimpleName();
        String maskQn = pkg + ".impl." + simple + "Mask";
        IType maskType = null;
        try {
            Class<?> maskClass = Class.forName(maskQn);
            maskType = PotatoTypes.getInstance().loadType(maskClass);
        } catch (ClassNotFoundException e) {
        }

        Map<String, Item> items = new LinkedHashMap<>();

        Table aTable = clazz.getAnnotation(Table.class);
        String tableName = aTable.name();

        for (Field field : clazz.getDeclaredFields()) {
            Item item = new Item();
            String name = field.getName();
            item.name = name;
            item.sqlName = name;
            item.type = field.getType();

            item.hasSqlCast = type.getProperty(name + "_sql") != null;
            if (item.hasSqlCast)
                item.name_sql = name + "_sql";

            if (maskType != null) {
                item.hasMain = maskType.getProperty(name) != null;
                item.hasRange = maskType.getProperty(name + "Range") != null;
                item.hasPattern = maskType.getProperty(name + "Pattern") != null;
            }
            items.put(item.name, item);
        }

        System.out.printf(TAB1 + "<resultMap id=\"objlist_map\" type=\"%s\">\n", clazz.getName());
        {
            for (Item item : items.values()) {
                System.out.println(TAB2 + item.result());
            }
            System.out.println(TAB1 + "</resultMap>");
            System.out.println();
        }

        System.out.println(TAB1 + "<sql id=\"filtconds\">");
        {
            // System.out.println("<!-- co -->");
            // System.out.println("<include refid=\"co.modefilt\" />");
//            System.out.println("<include refid=\"co.filter-id\" />");
//            System.out.println("<include refid=\"co.filter-ui\" />");
//            System.out.println("<include refid=\"co.filter-version\" />");
//            System.out.println("<include refid=\"message.filter-all\" />");

            for (Item item : items.values()) {
                System.out.print(item.filter());
            }
            System.out.println(TAB1 + "</sql>");
            System.out.println();
        }

        System.out.printf(TAB1 + "<insert id=\"insert\" useGeneratedKeys=\"true\" keyProperty=\"id\"><![CDATA[\n");
        {
            System.out.printf(TAB2 + "insert into %s(\n", tableName);
            boolean first = true;
            for (Item item : items.values()) {
                if (!first)
                    System.out.print(",\n");
                System.out.print(TAB3 + item.insertKey());
                first = false;
            }
            System.out.println();

            System.out.println(TAB2 + ") values(");
            first = true;
            for (Item item : items.values()) {
                if (!first)
                    System.out.print(",\n");
                System.out.print(TAB3 + item.insertVal());
                first = false;
            }
            System.out.println();

            System.out.println(TAB2 + ") returning id; --");
            System.out.println(TAB1 + "]]></insert>");
            System.out.println();
        }

        System.out.println(TAB1 + "<update id=\"update\">");
        {
            System.out.printf(TAB2 + "update %s\n", tableName);
            System.out.println(TAB2 + "<set>");
            // System.out.println(TAB2+"<include refid=\"co.setUS\" />");
            for (Item item : items.values()) {
                System.out.println(TAB3 + item.update());
            }
            System.out.println(TAB2 + "</set>");
            System.out.println(TAB2 + "<where>");
            System.out.println(TAB2 + "    id = #{id}");
            System.out.println(TAB2 + "</where>");
            System.out.println(TAB1 + "</update>");
            System.out.println();
        }

    }

}

class Item {
    String name;
    String name_sql;
    String description;
    Class<?> type;
    boolean optional;
    String sqlName;
    boolean hasSqlCast;
    boolean hasMain;
    boolean hasRange;
    boolean hasPattern;

    static String TAB1 = "    ";
    static String TAB2 = "        ";
    static String TAB3 = "            ";

    String result() {
        return String.format("<result property=\"%s\" column=\"%s\" />", //
                (hasSqlCast ? name_sql : name), sqlName);
    }

    String insertKey() {
        return sqlName;
    }

    String insertVal() {
        return "#{" + (hasSqlCast ? name_sql : name) + "}";
    }

    String update() {
        return String.format("%s = #{%s},", sqlName, (hasSqlCast ? name_sql : name));
    }

    String filter() {
        StringBuilder sb = new StringBuilder(100);

        switch (TypeKind.getTypeId(type)) {
        case TypeId._byte:
        case TypeId._short:
        case TypeId._long:
        case TypeId._double:
        case TypeId._float:
        case TypeId._int:
        case TypeId.BYTE:
        case TypeId.SHORT:
        case TypeId.INTEGER:
        case TypeId.LONG:
        case TypeId.FLOAT:
        case TypeId.DOUBLE:

        case TypeId.BIG_INTEGER:
        case TypeId.BIG_DECIMAL:

        case TypeId.DATE:
        case TypeId.SQL_DATE:
        case TypeId.JODA_DATETIME:

            if (hasMain) {
                sb.append(
                        String.format(TAB2 + "<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", name, sqlName, name));
            }
            if (hasRange) {
                String range = name + "Range";
                sb.append(String.format(TAB2 + "<if test=\"m.%s!= null\">\n", range));
                sb.append(String.format(TAB3 + "<if test=\"m.%s.hasStartIncl\">and a.%s >= #{m.%s.start}</if>\n", range,
                        sqlName, range));
                sb.append(String.format(TAB3 + "<if test=\"m.%s.hasStartExcl\">and a.%s > #{m.%s.start}</if>\n", range,
                        sqlName, range));
                sb.append(String.format(TAB3 + "<if test=\"m.%s.hasEndIncl\">and a.%s &lt;= #{m.%s.end}</if>\n", range,
                        sqlName, range));
                sb.append(String.format(TAB3 + "<if test=\"m.%s.hasEndExcl\">and a.%s &lt; #{m.%s.end}</if>\n", range,
                        sqlName, range));
                sb.append(String.format(TAB2 + "</if>\n", range, sqlName, range));
            }
            break;

        case TypeId.STRING:
            if (hasMain)
                sb.append(
                        String.format(TAB2 + "<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", name, sqlName, name));
            if (hasPattern)
                sb.append(String.format(TAB2 + "<if test=\"m.%sPattern != null\">and %s like '${m.%sPattern}'</if>\n", //
                        name, sqlName, name));
            break;

        // case TypeId.ENUM:

        case TypeId._char:
        case TypeId.CHARACTER:

        case TypeId._boolean:
        case TypeId.BOOLEAN:
        default:
            sb.append(String.format(TAB2 + "<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                    name, sqlName, name));
        }
        return sb.toString();
    }

}