package net.bodz.violet.store;

import java.util.Set;

import javax.persistence.Table;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;
import net.bodz.lily.model.contact.Organization;
import net.bodz.lily.model.contact.Person;

/**
 * 存放区域
 */
@IdType(Integer.class)
@Table(name = "region")
public class Region
        extends CoNode<Region, Integer> {

    private static final long serialVersionUID = 1L;

    public static final int ID_AbstractRoot = 1;
    public static final int ID_TemplateRoot = 2;

    private Artifact asArtifact;
    private ArtifactCategory forArtifactCategory;
    private Artifact forArtifact;
    private RegionCategory category;
    private Set<RegionTag> tags;

    private Dim3d position = new Dim3d();
    private Dim3d bbox = new Dim3d();
    private Person person;
    private Organization org;

    public Artifact getAsArtifact() {
        return asArtifact;
    }

    public void setAsArtifact(Artifact asArtifact) {
        this.asArtifact = asArtifact;
    }

    public ArtifactCategory getForArtifactCategory() {
        return forArtifactCategory;
    }

    public void setForArtifactCategory(ArtifactCategory forArtifactCategory) {
        this.forArtifactCategory = forArtifactCategory;
    }

    public Artifact getForArtifact() {
        return forArtifact;
    }

    public void setForArtifact(Artifact forArtifact) {
        this.forArtifact = forArtifact;
    }

    public RegionCategory getCategory() {
        return category;
    }

    public void setCategory(RegionCategory category) {
        this.category = category;
    }

    public Set<RegionTag> getTags() {
        return tags;
    }

    public void setTags(Set<RegionTag> tags) {
        this.tags = tags;
    }

    /**
     * 位置
     * 
     * 用三维笛卡尔坐标系表示的空间位置，对齐到库位的基点。
     * <p>
     * 库位的基点通常选择库位底部的左下角，或垂直于地球表面向下的底部的正西南角。
     * <p>
     * 坐标轴的原点通常选择或总仓库一层地面的正西南角，或地球的中心点（用经纬度表示的数值）。
     * <p>
     * 坐标的单位可以自行设定，通常使用<code>米</code>，或<code>经度</code>(X)、<code>纬度</code>(Y)。
     */
    public Dim3d getPosition() {
        return position;
    }

    public void setPosition(Dim3d position) {
        if (position == null)
            throw new NullPointerException("position");
        this.position = position;
    }

    /**
     * 尺寸
     * 
     * 可以用来容纳物件的空间尺寸。
     */
    public Dim3d getBbox() {
        return bbox;
    }

    public void setBbox(Dim3d bbox) {
        if (bbox == null)
            throw new NullPointerException("bbox");
        this.bbox = bbox;
    }

    /**
     * 代理人
     * 
     * 说明这个库位是为指定代理人（供应商或客户）服务的，专门用来存放该代理人的货品。
     */
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * 代理企业
     * 
     * 说明这个库位是为指定企业（供货商或客户）服务的，专门用来存放该企业的货品。
     */
    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);

        IVariantMap<String> asArt = (IVariantMap<String>) map.get("asArtifact");
        if (asArt != null) {
            Integer id = asArt.getInt("id", asArtifact == null ? null : asArtifact.getId());
            if (id != null) {
                asArtifact = new Artifact();
                asArtifact.setId(id);
            } else
                asArtifact = null;
        }

        IVariantMap<String> forArtCat = (IVariantMap<String>) map.get("forArtifactCategory");
        if (forArtCat != null) {
            Integer id = forArtCat.getInt("id", forArtifactCategory == null ? null : forArtifactCategory.getId());
            if (id != null) {
                forArtifactCategory = new ArtifactCategory();
                forArtifactCategory.setId(id);
            } else
                forArtifactCategory = null;
        }

        IVariantMap<String> cat = (IVariantMap<String>) map.get("category");
        if (cat != null) {
            Integer id = cat.getInt("id", category == null ? null : category.getId());
            if (id != null) {
                category = new RegionCategory();
                category.setId(id);
            } else
                category = null;
        }

        // private Dim3d position = new Dim3d();
        // private Dim3d bbox = new Dim3d();
        // private Person person;
        // private Organization org;
    }

}
