package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _CategoryDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_PARENT_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_SCHEMA_ID = _ord_ID + 10;
    private static final int _ord_PARENT_ID = _ord_SCHEMA_ID + 1;
    private static final int _ord_DEPTH = _ord_PARENT_ID + 1;
    private static final int _ord_OBJ_COUNT = _ord_DEPTH + 2;

    @Id
    @NotNull
    int id;

    @NotNull
    int depth;

    @NotNull
    int objCount;

    /**  */
    @NotNull
    SchemaDef schema;

    @NotNull
    int schemaId;

    /**  */
    CategoryDef parent;

    Integer parentId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_DEPTH)
    @Precision(value = 10)
    @Column(name = "depth", nullable = false, precision = 10)
    public int getDepth() {
        return depth;
    }

    public void setDepth(int value) {
        this.depth = value;
    }

    @Ordinal(_ord_OBJ_COUNT)
    @Precision(value = 10)
    @Column(name = "nobj", nullable = false, precision = 10)
    public int getObjCount() {
        return objCount;
    }

    public void setObjCount(int value) {
        this.objCount = value;
    }

    /**
     *
     * @label schema
     * @constraint foreign key (schema) references lily._schema (id)
     */
    @NotNull
    public SchemaDef getSchema() {
        return schema;
    }

    /**
     */
    public void setSchema(@NotNull SchemaDef value) {
        this.schema = value;
    }

    @Ordinal(_ord_SCHEMA_ID)
    @Precision(value = 10)
    @Column(name = "schema", nullable = false, precision = 10)
    public synchronized int getSchemaId() {
        if (schema != null) {
            if (schema.getId() == null)
                return 0;
            return schema.getId();
        }
        return schemaId;
    }

    public synchronized void setSchemaId(int value) {
        this.schemaId = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references lily._cat (id)
     */
    public CategoryDef getParent() {
        return parent;
    }

    /**
     */
    public void setParent(CategoryDef value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 10)
    public synchronized Integer getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Integer value) {
        this.parentId = value;
    }

    public void initNotNulls() {
    }

}
