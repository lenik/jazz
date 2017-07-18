package net.bodz.violet.store;

import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.model.base.CoRelation;

/**
 * 库位选项
 */
public class ArtifactRegionOption
        extends CoRelation<Integer> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    Region region;
    boolean locked;
    String status;

    double reservation;
    int checkPeriod;
    long checkExpires;

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * @label 永久库位
     */
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * @label 推荐库位状态
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 安全库存
     * 
     * @label 库存安全保留量
     */
    @OfGroup(StdGroup.Schedule.class)
    public double getReservation() {
        return reservation;
    }

    public void setReservation(double reservation) {
        this.reservation = reservation;
    }

    /**
     * @label 盘点周期
     */
    @OfGroup(StdGroup.Schedule.class)
    public int getCheckPeriod() {
        return checkPeriod;
    }

    public void setCheckPeriod(int checkPeriod) {
        this.checkPeriod = checkPeriod;
    }

    /**
     * @label 下次盘点时间
     */
    @OfGroup(StdGroup.Schedule.class)
    public long getCheckExpires() {
        return checkExpires;
    }

    public void setCheckExpires(long checkExpires) {
        this.checkExpires = checkExpires;
    }

}
