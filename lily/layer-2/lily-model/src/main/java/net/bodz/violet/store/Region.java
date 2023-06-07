package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.bas.site.file.UploadHint;
import net.bodz.violet.art.Dim3d;

/**
 * 存放区域
 */
@UploadHint
@Table(schema = "violet", name = "region")
public class Region
        extends _Region_stuff<Region> {

    private static final long serialVersionUID = 1L;

    public static final int ID_AbstractRoot = 1;
    public static final int ID_TemplateRoot = 2;

    public static final int LEVEL_Default = 0;

    private String fullPath = "";
    private RegionProperties properties = new RegionProperties();

    private Dim3d position = new Dim3d();
    private Dim3d bbox = new Dim3d();
    /**
     * redundant.
     */
    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
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
}
