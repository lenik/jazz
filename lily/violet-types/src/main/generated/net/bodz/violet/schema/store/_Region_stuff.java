package net.bodz.violet.schema.store;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoNode;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.Artifact;
import net.bodz.violet.schema.art.ArtifactCategory;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _Region_stuff<this_t extends _Region_stuff<this_t>>
        extends CoNode<this_t, Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "region";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_PATH = "path";
    public static final String FIELD_PROTO_ID = "proto";
    public static final String FIELD_HEIGHT = "height";
    public static final String FIELD_LEVEL_ID = "level";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_TAG_ID = "tag";
    public static final String FIELD_MATERIAL_ID = "material";
    public static final String FIELD_FOR_CAT_ID = "for_cat";
    public static final String FIELD_FOR_ART_ID = "for_art";
    public static final String FIELD_ARTIFACT_ID = "art";

    public static final int N_CODE = 10;
    public static final int N_PATH = 200;
    public static final int N_PROTO_ID = 10;
    public static final int N_HEIGHT = 10;
    public static final int N_LEVEL_ID = 10;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_TAG_ID = 10;
    public static final int N_MATERIAL_ID = 10;
    public static final int N_FOR_CAT_ID = 10;
    public static final int N_FOR_ART_ID = 10;
    public static final int N_ARTIFACT_ID = 10;

    private static final int _ord_CODE = 2;
    private static final int _ord_PATH = 18;
    private static final int _ord_PROTO_ID = _ord_PATH + 1;
    private static final int _ord_HEIGHT = _ord_PROTO_ID + 3;
    private static final int _ord_LEVEL_ID = _ord_HEIGHT + 1;
    private static final int _ord_CATEGORY_ID = _ord_LEVEL_ID + 1;
    private static final int _ord_TAG_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_MATERIAL_ID = _ord_TAG_ID + 7;
    private static final int _ord_FOR_CAT_ID = _ord_MATERIAL_ID + 1;
    private static final int _ord_FOR_ART_ID = _ord_FOR_CAT_ID + 1;
    private static final int _ord_ARTIFACT_ID = _ord_FOR_ART_ID + 1;

    String code;

    @NotNull
    String path;

    @NotNull
    int height;

    /**  */
    ArtifactCategory forCat;

    Integer forCatId;

    /**  */
    Region proto;

    Integer protoId;

    /**  */
    RegionTag tag;

    Integer tagId;

    /**  */
    @NotNull
    RegionCategory category;

    @NotNull
    int categoryId;

    /**  */
    @NotNull
    RegionLevel level;

    @NotNull
    int levelId;

    /**  */
    Artifact artifact;

    Integer artifactId;

    /**  */
    Artifact material;

    Integer materialId;

    /**  */
    Artifact forArt;

    Integer forArtId;

    @Ordinal(_ord_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    @Column(name = "code", length = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    @Ordinal(_ord_PATH)
    @NotNull
    @Precision(value = N_PATH)
    @TextInput(maxLength = N_PATH)
    @Column(name = "path", nullable = false, length = N_PATH)
    public String getPath() {
        return path;
    }

    public void setPath(@NotNull String value) {
        this.path = value;
    }

    @Ordinal(_ord_HEIGHT)
    @Precision(value = 10)
    @Column(name = "height", nullable = false, precision = 10)
    public int getHeight() {
        return height;
    }

    public void setHeight(int value) {
        this.height = value;
    }

    /**
     *
     * @constraint foreign key (for_cat) references violet.artcat (id)
     */
    @JoinColumn(name = "for_cat")
    @ManyToOne
    public ArtifactCategory getForCat() {
        return forCat;
    }

    /**
     */
    public void setForCat(ArtifactCategory value) {
        this.forCat = value;
    }

    @Ordinal(_ord_FOR_CAT_ID)
    @Precision(value = N_FOR_CAT_ID)
    @Column(name = "for_cat", precision = 10)
    public synchronized Integer getForCatId() {
        if (forCat != null) {
            return forCat.getId();
        }
        return forCatId;
    }

    public synchronized void setForCatId(Integer value) {
        this.forCatId = value;
    }

    /**
     *
     * @constraint foreign key (proto) references violet.region (id)
     */
    @JoinColumn(name = "proto")
    @ManyToOne
    public Region getProto() {
        return proto;
    }

    /**
     */
    public void setProto(Region value) {
        this.proto = value;
    }

    @Ordinal(_ord_PROTO_ID)
    @Precision(value = N_PROTO_ID)
    @Column(name = "proto", precision = 10)
    public synchronized Integer getProtoId() {
        if (proto != null) {
            return proto.getId();
        }
        return protoId;
    }

    public synchronized void setProtoId(Integer value) {
        this.protoId = value;
    }

    /**
     *
     * @constraint foreign key (tag) references violet.regiontag (id)
     */
    @JoinColumn(name = "tag")
    @ManyToOne
    public RegionTag getTag() {
        return tag;
    }

    /**
     */
    public void setTag(RegionTag value) {
        this.tag = value;
    }

    @Ordinal(_ord_TAG_ID)
    @Precision(value = N_TAG_ID)
    @Column(name = "tag", precision = 10)
    public synchronized Integer getTagId() {
        if (tag != null) {
            return tag.getId();
        }
        return tagId;
    }

    public synchronized void setTagId(Integer value) {
        this.tagId = value;
    }

    /**
     *
     * @constraint foreign key (cat) references violet.regioncat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public RegionCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull RegionCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (level) references violet.regionlevel (id)
     */
    @JoinColumn(name = "level")
    @ManyToOne
    @NotNull
    public RegionLevel getLevel() {
        return level;
    }

    /**
     */
    public void setLevel(@NotNull RegionLevel value) {
        this.level = value;
    }

    @Ordinal(_ord_LEVEL_ID)
    @Precision(value = 10)
    @Column(name = "level", nullable = false, precision = 10)
    public synchronized int getLevelId() {
        if (level != null) {
            if (level.getId() == null)
                return 0;
            return level.getId();
        }
        return levelId;
    }

    public synchronized void setLevelId(int value) {
        this.levelId = value;
    }

    /**
     *
     * @constraint foreign key (art) references violet.art (id)
     */
    @JoinColumn(name = "art")
    @ManyToOne
    public Artifact getArtifact() {
        return artifact;
    }

    /**
     */
    public void setArtifact(Artifact value) {
        this.artifact = value;
    }

    @Ordinal(_ord_ARTIFACT_ID)
    @Precision(value = N_ARTIFACT_ID)
    @Column(name = "art", precision = 10)
    public synchronized Integer getArtifactId() {
        if (artifact != null) {
            return artifact.getId();
        }
        return artifactId;
    }

    public synchronized void setArtifactId(Integer value) {
        this.artifactId = value;
    }

    /**
     *
     * @constraint foreign key (material) references violet.art (id)
     */
    @JoinColumn(name = "material")
    @ManyToOne
    public Artifact getMaterial() {
        return material;
    }

    /**
     */
    public void setMaterial(Artifact value) {
        this.material = value;
    }

    @Ordinal(_ord_MATERIAL_ID)
    @Precision(value = N_MATERIAL_ID)
    @Column(name = "material", precision = 10)
    public synchronized Integer getMaterialId() {
        if (material != null) {
            return material.getId();
        }
        return materialId;
    }

    public synchronized void setMaterialId(Integer value) {
        this.materialId = value;
    }

    /**
     *
     * @constraint foreign key (for_art) references violet.art (id)
     */
    @JoinColumn(name = "for_art")
    @ManyToOne
    public Artifact getForArt() {
        return forArt;
    }

    /**
     */
    public void setForArt(Artifact value) {
        this.forArt = value;
    }

    @Ordinal(_ord_FOR_ART_ID)
    @Precision(value = N_FOR_ART_ID)
    @Column(name = "for_art", precision = 10)
    public synchronized Integer getForArtId() {
        if (forArt != null) {
            return forArt.getId();
        }
        return forArtId;
    }

    public synchronized void setForArtId(Integer value) {
        this.forArtId = value;
    }

    public void initNotNulls() {
        this.path = "";
    }

}
