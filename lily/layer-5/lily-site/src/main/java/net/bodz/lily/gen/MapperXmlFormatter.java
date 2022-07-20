package net.bodz.lily.gen;

import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.io.ITreeOut;
import net.bodz.lily.gen.model.java.reflect.EntityClassModel;
import net.bodz.lily.gen.model.java.reflect.EntityFieldModel;
import net.bodz.lily.gen.model.java.reflect.MaskFieldModel;

public class MapperXmlFormatter {

    ITreeOut out;

    public MapperXmlFormatter(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    public void mapperXml(String qName, EntityClassModel entity) {
        if (entity == null)
            throw new NullPointerException("entity");

        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<!DOCTYPE mapper");
        out.println("PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
        out.println("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");

        String NS = qName;
        out.println("<mapper namespace=\"" + NS + "\">");
        out.enter();
        {
            out.println();
            resultMap_objlist_map(entity);
            out.println();
            sql_objlist_sql(entity);
            out.println();
            sql_objedit_sql(entity);
            out.println();
            sql_filtconds(entity);
            out.println();
            select_all(entity);
            out.println();
            select_filter(entity);
            out.println();
            select(entity);
            out.println();
            insert(entity);
            out.println();
            update(entity);
            out.println();
            delete(entity);
            out.println();
            select_count(entity);
            out.println();
            out.leave();
        }
        out.println("</mapper>");
    }

    void resultMap_objlist_map(EntityClassModel entity) {
        out.printf("<resultMap id=\"objlist_map\" type=\"%s\">\n", entity.declaringClass.getName());
        out.enter();
        {
            for (EntityFieldModel field : entity.getFields()) {
                out.printf("<result property=\"%s\" column=\"%s\" />\n", //
                        field.getFieldNameForSql(), field.columnName);
            }
            out.leave();
        }
        out.println("</resultMap>");
    }

    void sql_objlist_sql(EntityClassModel entity) {
        out.println("<sql id=\"objlist_sql\"><![CDATA[");
        out.enter();
        {
            out.println("select");
            out.enter();
            {
                out.println("a.*");
                out.leave();
            }
            out.println("from " + entity.getQualifiedName() + " a");
            out.println("]]>");
            out.leave();
        }
        out.println("</sql>");
    }

    void sql_objedit_sql(EntityClassModel entity) {
        out.println("<sql id=\"objedit_sql\"><![CDATA[");
        out.enter();
        {
            out.println("select");
            out.enter();
            {
                out.println("a.*");
                out.leave();
            }
            out.println("from " + entity.getQualifiedName() + " a");
            // out.enter();
            // out.println("left join zj_qfx_dm_mx m on a.mx_dm=m.mx_dm");
            // out.leave();
            out.println("]]>");
            out.leave();
        }
        out.println("</sql>");
    }

    void sql_filtconds(EntityClassModel entity) {
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

    void select_all(EntityClassModel entity) {
        out.println("<select id=\"all\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objlist_sql\" />");
            out.leave();
        }
        out.println("</select>");
    }

    void select_filter(EntityClassModel entity) {
        out.println("<select id=\"filter\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objlist_sql\" />");
            out.println("<where>");
            out.enter();
            {
                out.println("<include refid=\"filtconds\" />");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
    }

    void select(EntityClassModel entity) {
        out.println("<select id=\"select\" resultMap=\"objlist_map\">");
        out.enter();
        {
            out.println("<include refid=\"objedit_sql\" />");
            out.println("<where>");
            out.enter();
            {
                out.println("<if test=\"_parameter != null\">a.id = #{id}</if>");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
    }

    void insert(EntityClassModel entity) {
        out.printf("<insert id=\"insert\" useGeneratedKeys=\"true\" keyProperty=\"id\"><![CDATA[\n");
        out.enter();
        {
            out.printf("insert into %s(\n", entity.getQualifiedName());
            {
                out.enter();
                boolean first = true;
                for (EntityFieldModel item : entity.getFields()) {
                    if (!first)
                        out.print(",\n");

                    // insertKey
                    out.print(item.columnName);

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

                    // insertVal
                    out.print("#{" + item.getFieldNameForSql() + "}");

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
            out.printf("update %s\n", entity.getQualifiedName());
            out.println("<set>");
            out.enter();
            {
                boolean co = false;
                if (co)
                    out.println("<include refid=\"co.setUS\" />");
                for (EntityFieldModel field : entity.getFields()) {
                    out.printf("%s = #{%s}", field.columnName, field.getFieldNameForSql());
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

    void delete(EntityClassModel table) {
        out.println("<delete id=\"delete\">");
        out.enter();
        {
            out.println("delete from " + table.getQualifiedName() + " where id = #{id}");
            out.leave();
        }
        out.println("</delete>");
    }

    void select_count(EntityClassModel table) {
        out.println("<select id=\"count\" resultType=\"hashmap\">");
        out.enter();
        {
            out.println("select count(*) \"rows\" from " + table.getQualifiedName());
            out.println("<where>");
            out.enter();
            {
                out.println("<if test=\"_parameter != null\">");
                out.enter();
                {
                    out.println("<include refid=\"filtconds\" />");
                    out.leave();
                }
                out.println("</if>");
                out.leave();
            }
            out.println("</where>");
            out.leave();
        }
        out.println("</select>");
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