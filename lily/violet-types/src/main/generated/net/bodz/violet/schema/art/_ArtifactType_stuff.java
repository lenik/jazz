package net.bodz.violet.schema.art;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.util.Uom;

@IdType(Integer.class)
public abstract class _ArtifactType_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "arttype";

    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_UOM_ID = "uom";

    public static final int N_CATEGORY_ID = 10;
    public static final int N_PARENT_ID = 10;
    public static final int N_UOM_ID = 10;

    private static final int _ord_CATEGORY_ID = 15;
    private static final int _ord_PARENT_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_UOM_ID = _ord_PARENT_ID + 1;

    /**  */
    Uom uom;

    Integer uomId;

    /**  */
    ArtifactType parent;

    Integer parentId;

    /**  */
    ArtifactCategory category;

    Integer categoryId;

    /**
     *
     * @constraint foreign key (uom) references lily.uom (id)
     */
    @JoinColumn(name = "uom")
    @ManyToOne
    public Uom getUom() {
        return uom;
    }

    /**
     */
    public void setUom(Uom value) {
        this.uom = value;
    }

    @Ordinal(_ord_UOM_ID)
    @Precision(value = N_UOM_ID)
    @Column(name = "uom", precision = 10)
    public synchronized Integer getUomId() {
        if (uom != null) {
            return uom.getId();
        }
        return uomId;
    }

    public synchronized void setUomId(Integer value) {
        this.uomId = value;
    }

    /**
     *
     * @constraint foreign key (parent) references violet.arttype (id)
     */
    @JoinColumn(name = "parent")
    @ManyToOne
    public ArtifactType getParent() {
        return parent;
    }

    /**
     */
    public void setParent(ArtifactType value) {
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

    /**
     *
     * @constraint foreign key (cat) references violet.artcat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    public ArtifactCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(ArtifactCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = N_CATEGORY_ID)
    @Column(name = "cat", precision = 10)
    public synchronized Integer getCategoryId() {
        if (category != null) {
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(Integer value) {
        this.categoryId = value;
    }

    public void initNotNulls() {
    }

}
