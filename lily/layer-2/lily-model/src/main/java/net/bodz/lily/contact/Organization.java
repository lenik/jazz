package net.bodz.lily.contact;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;

import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.bas.repr.form.meta.TextInput;

/**
 * 公司组织
 */
@Table(name = "org")
public class Organization
        extends Party {

    private static final long serialVersionUID = 1L;

    public static final int N_TAXID = 20;

    private int size;
    private String taxId;

    private boolean shipper;

    private List<Person> staff = new ArrayList<>();
    private List<OrgUnit> topLevels = new ArrayList<OrgUnit>();

    /**
     * 全名
     */
    @TextInput(maxLength = N_LABEL)
    @OfGroup(StdGroup.Identity.class)
    @Derived
    public String getFullName() {
        return getLabel();
    }

    public void setFullName(String fullName) {
        setLabel(fullName);
    }

    /**
     * 主营业务
     *
     * @placeholder 输入主营业务…
     */
    @Priority(300)
    @TextInput(maxLength = N_SUBJECT)
    @Override
    public String getSubject() {
        return super.getSubject();
    }

    /**
     * 描述企业的规模（人数）。
     *
     * @label 规模
     * @format ###人
     */
    @Priority(310)
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 标记 - 承运人
     */
    @OfGroup(StdGroup.Classification.class)
    public boolean isShipper() {
        return shipper;
    }

    public void setShipper(boolean shipper) {
        this.shipper = shipper;
    }

    /**
     * 税号
     *
     * @placeholder 输入税号…
     * @format ######-###-###-###
     */
    @TextInput(maxLength = N_TAXID)
    @OfGroup(StdGroup.Identity.class)
    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    /**
     * 人员表
     */
    @Priority(400)
    @DetailLevel(DetailLevel.DETAIL2)
    @Derived
    public List<Person> getStaff() {
        return staff;
    }

    /**
     * 顶级机构
     */
    @Priority(400)
    public List<OrgUnit> getTopLevels() {
        return topLevels;
    }

    // TODO xjdoc don't inherit the docs from the super method.
    /**
     * 由一系列单字符描述的分类特征。
     *
     * @label Characters
     * @label.zh 特征字
     */
    @OfGroup(StdGroup.Classification.class)
    @Derived
    @Override
    public String getTypeChars() {
        String typeChars = super.getTypeChars();
        if (shipper)
            typeChars += "运";
        return typeChars;
    }

}
