package net.bodz.lily.gen;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.io.ITreeOut;
import net.bodz.lily.gen.model.java.EntityClassModel;
import net.bodz.lily.gen.model.java.EntityFieldModel;
import net.bodz.lily.gen.model.java.MaskFieldModel;

public class MapperXmlFormatter {

    ITreeOut out;

    public MapperXmlFormatter(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public void mapperXml(EntityClassModel entity) {
        if (entity == null)
            throw new NullPointerException("entity");
        out.enter();
        resultMap(entity);
        out.println();
        filtconds(entity);
        out.println();
        insert(entity);
        out.println();
        update(entity);
        out.leave();
    }

    void resultMap(EntityClassModel entity) {
        out.printf("<resultMap id=\"objlist_map\" type=\"%s\">\n", entity.declaringClass.getName());
        out.enter();
        {
            for (EntityFieldModel field : entity.getFields()) {
                result(field);
            }
            out.leave();
        }
        out.println("</resultMap>");
    }

    void filtconds(EntityClassModel entity) {
        out.println("<sql id=\"filtconds\">");
        out.enter();
        {
            boolean includeCo = false;
            if (includeCo) {
                out.println("<!-- co -->");
                out.println("<include refid=\"co.modefilt\" />");
                out.println("<include refid=\"co.filter-id\" />");
                out.println("<include refid=\"co.filter-ui\" />");
                out.println("<include refid=\"co.filter-version\" />");
                out.println("<include refid=\"message.filter-all\" />");
            }

            for (EntityFieldModel field : entity.getFields()) {
                filter(field);
            }
            out.leave();
        }
        out.println("</sql>");
    }

    void insert(EntityClassModel entity) {
        out.printf("<insert id=\"insert\" useGeneratedKeys=\"true\" keyProperty=\"id\"><![CDATA[\n");
        out.enter();
        {
            out.printf("insert into %s(\n", entity.tableName);
            {
                out.enter();
                boolean first = true;
                for (EntityFieldModel item : entity.getFields()) {
                    if (!first)
                        out.print(",\n");
                    insertKey(item);
                    first = false;
                }
                out.leave();
                out.println();
            }

            out.println(") values(");
            {
                out.enter();
                boolean first = true;
                for (EntityFieldModel item : entity.getFields()) {
                    if (!first)
                        out.print(",\n");
                    insertVal(item);
                    first = false;
                }
                out.leave();
                out.println();
            }

            out.println(") returning id; --");
            out.leave();
        }
        out.println("]]></insert>");
    }

    void update(EntityClassModel entity) {
        out.println("<update id=\"update\">");
        {
            out.enter();
            out.printf("update %s\n", entity.tableName);
            out.println("<set>");
            out.enter();
            {
                boolean co = false;
                if (co)
                    out.println("<include refid=\"co.setUS\" />");
                for (EntityFieldModel item : entity.getFields()) {
                    update(item);
                    out.println(",");
                }
                out.leave();
            }
            out.println("</set>");

            out.println("<where>");
            out.enter();
            {
                out.println("    id = #{id}");
                out.leave();
            }
            out.println("</where>");
        }
        out.leave();
        out.println("</update>");
    }

    void result(EntityFieldModel field) {
        out.printf("<result property=\"%s\" column=\"%s\" />\n", //
                field.getFieldNameForSql(), field.columnName);
    }

    void insertKey(EntityFieldModel field) {
        out.print(field.columnName);
    }

    void insertVal(EntityFieldModel field) {
        out.print("#{" + field.getFieldNameForSql() + "}");
    }

    void update(EntityFieldModel field) {
        out.printf("%s = #{%s}", field.columnName, field.getFieldNameForSql());
    }

    void filter(EntityFieldModel field) {
        MaskFieldModel mask = field.mask;

        switch (TypeKind.getTypeId(field.fieldType)) {
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

            if (mask.hasMain) {
                out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                        mask.name, field.columnName, mask.name);
            }

            if (mask.hasRange) {
                String range = mask.name + "Range";
                out.printf("<if test=\"m.%s!= null\">\n", range);
                out.enter();
                out.printf("<if test=\"m.%s.hasStartIncl\">and a.%s >= #{m.%s.start}</if>\n", //
                        range, field.columnName, range);
                out.printf("<if test=\"m.%s.hasStartExcl\">and a.%s > #{m.%s.start}</if>\n", //
                        range, field.columnName, range);
                out.printf("<if test=\"m.%s.hasEndIncl\">and a.%s &lt;= #{m.%s.end}</if>\n", //
                        range, field.columnName, range);
                out.printf("<if test=\"m.%s.hasEndExcl\">and a.%s &lt; #{m.%s.end}</if>\n", //
                        range, field.columnName, range);
                out.leave();
                out.println("</if>");
            }
            break;

        case TypeId.STRING:
            if (mask.hasMain)
                out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                        mask.name, field.columnName, mask.name);
            if (mask.hasPattern)
                out.printf("<if test=\"m.%sPattern != null\">and %s like '${m.%sPattern}'</if>\n", //
                        mask.name, field.columnName, mask.name);
            break;

        // case TypeId.ENUM:

        case TypeId._char:
        case TypeId.CHARACTER:

        case TypeId._boolean:
        case TypeId.BOOLEAN:
        default:
            out.printf("<if test=\"m.%s != null\">and %s = #{m.%s}</if>\n", //
                    mask.name, field.columnName, mask.name);
        }
    }

}