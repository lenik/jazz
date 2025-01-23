package net.bodz.violet.schema.shop;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;

@IdType(Integer.class)
public abstract class _Shop_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "shop";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_SUPPLIER_ORG_ID = "supplierorg";
    public static final String FIELD_SUPPLIER_ID = "supplier";
    public static final String FIELD_HYDM = "hydm";

    public static final int N_CODE = 30;
    public static final int N_SUPPLIER_ORG_ID = 10;
    public static final int N_SUPPLIER_ID = 10;
    public static final int N_HYDM = 10;

    private static final int _ord_CODE = 15;
    private static final int _ord_SUPPLIER_ORG_ID = _ord_CODE + 3;
    private static final int _ord_SUPPLIER_ID = _ord_SUPPLIER_ORG_ID + 1;
    private static final int _ord_HYDM = _ord_SUPPLIER_ID + 1;

    String code;

    Integer hydm;

    /**  */
    Person supplier;

    Integer supplierId;

    /**  */
    Organization supplierOrg;

    Integer supplierOrgId;

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

    @Ordinal(_ord_HYDM)
    @Precision(value = N_HYDM)
    @Column(name = "hydm", precision = 10)
    public Integer getHydm() {
        return hydm;
    }

    public void setHydm(Integer value) {
        this.hydm = value;
    }

    /**
     *
     * @constraint foreign key (supplier) references lily.person (id)
     */
    @JoinColumn(name = "supplier")
    @ManyToOne
    public Person getSupplier() {
        return supplier;
    }

    /**
     */
    public void setSupplier(Person value) {
        this.supplier = value;
    }

    @Ordinal(_ord_SUPPLIER_ID)
    @Precision(value = N_SUPPLIER_ID)
    @Column(name = "supplier", precision = 10)
    public synchronized Integer getSupplierId() {
        if (supplier != null) {
            return supplier.getId();
        }
        return supplierId;
    }

    public synchronized void setSupplierId(Integer value) {
        this.supplierId = value;
    }

    /**
     *
     * @constraint foreign key (supplierorg) references lily.org (id)
     */
    @JoinColumn(name = "supplierorg")
    @ManyToOne
    public Organization getSupplierOrg() {
        return supplierOrg;
    }

    /**
     */
    public void setSupplierOrg(Organization value) {
        this.supplierOrg = value;
    }

    @Ordinal(_ord_SUPPLIER_ORG_ID)
    @Precision(value = N_SUPPLIER_ORG_ID)
    @Column(name = "supplierorg", precision = 10)
    public synchronized Integer getSupplierOrgId() {
        if (supplierOrg != null) {
            return supplierOrg.getId();
        }
        return supplierOrgId;
    }

    public synchronized void setSupplierOrgId(Integer value) {
        this.supplierOrgId = value;
    }

    public void initNotNulls() {
    }

}
