package net.bodz.lily.gen;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Table;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.program.skel.BasicCLI;

public class Gen
        extends BasicCLI {

    Class<?> type;

    @Override
    protected void mainImpl(String... args)
            throws Exception {

        Map<String, Item> items = new LinkedHashMap<>();

        Table aTable = type.getAnnotation(Table.class);
        String tableName = aTable.name();

        for (Field field : type.getDeclaredFields()) {
            Item item = new Item();
            item.name = field.getName();
            item.sqlName = item.name;
            item.type = field.getType();
            items.put(item.name, item);
        }

        System.out.printf(TAB1 + "<resultMap id=\"objlist_map\" type=\"%s\">\n", type.getName());
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
                System.out.println(TAB2 + item.filter());
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

    public static void main(String[] args)
            throws Exception {
        new Gen().execute(args);

    }

    static String TAB1 = "    ";
    static String TAB2 = "        ";
    static String TAB3 = "            ";
}

class Item {
    String name;
    String description;
    Class<?> type;
    boolean optional;
    String sqlName;

    String result() {
        return String.format("<result property=\"%s\" column=\"%s\" />", name, sqlName);
    }

    String insertKey() {
        return name;
    }

    String insertVal() {
        return "#{" + sqlName + "}";
    }

    String update() {
        return String.format("%s = #{%s},", sqlName, name);
    }

    String filter() {
        StringBuilder sb = new StringBuilder(100);

        switch (TypeKind.getTypeId(type)) {
        case TypeId._short:
        case TypeId._long:
        case TypeId._double:
        case TypeId._float:
        case TypeId._int:
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

            String range = name + "Range";
            sb.append(String.format("<if test=\"m.%s!= null\">\n", range));
            sb.append(String.format("                <if test=\"m.%s.hasStartIncl\">and a.%s >= #{m.%s.start}</if>\n",
                    range, sqlName, range));
            sb.append(String.format("                <if test=\"m.%s.hasStartExcl\">and a.%s > #{m.%s.start}</if>\n",
                    range, sqlName, range));
            sb.append(String.format("                <if test=\"m.%s.hasEndIncl\">and a.%s &lt;= #{m.%s.end}</if>\n",
                    range, sqlName, range));
            sb.append(String.format("                <if test=\"m.%s.hasEndExcl\">and a.%s &lt; #{m.%s.end}</if>\n",
                    range, sqlName, range));
            sb.append(String.format("            </if>", range, sqlName, range));
            break;

        // case TypeId.ENUM:

        case TypeId._char:
        case TypeId._byte:
        case TypeId.CHARACTER:
        case TypeId.BYTE:

        case TypeId._boolean:
        case TypeId.BOOLEAN:

        case TypeId.STRING:

        default:
            sb.append(String.format("<if test=\"m.%s != null\">and %s = #{m.%s}</if>", name, sqlName, name));
        }
        return sb.toString();
    }

}