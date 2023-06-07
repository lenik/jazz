package net.bodz.violet.tran.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import net.bodz.bas.t.range.BigDecimalRange;
import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.t.base.CoMessageMask;

public class _TransportOrderMask_stuff
        extends CoMessageMask {

    Integer ownerUserId;
    IntegerRange ownerUserIdRange = new IntegerRange();

    Integer accessMode;
    IntegerRange accessModeRange = new IntegerRange();

    Integer acl;
    IntegerRange aclRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    String subject;
    String subjectPattern;

    String rawText;
    String rawTextPattern;

    String formArguments;
    String formArgumentsPattern;

    Object properties;

    Long prevId;
    LongRange prevIdRange = new LongRange();

    Long salesOrderId;
    LongRange salesOrderIdRange = new LongRange();

    Long storeodrId;
    LongRange storeodrIdRange = new LongRange();

    Integer shipperId;
    IntegerRange shipperIdRange = new IntegerRange();

    String sAlias;
    String sAliasPattern;

    Object sCtprops;

    String sAddress1;
    String sAddress1Pattern;

    String sAddress2;
    String sAddress2Pattern;

    Integer sZoneId;
    IntegerRange sZoneIdRange = new IntegerRange();

    String sTel;
    String sTelPattern;

    Boolean sTelok;

    String sEmail;
    String sEmailPattern;

    Boolean sEmailok;

    String dAlias;
    String dAliasPattern;

    Object dCtprops;

    String dAddress1;
    String dAddress1Pattern;

    String dAddress2;
    String dAddress2Pattern;

    Integer dZoneId;
    IntegerRange dZoneIdRange = new IntegerRange();

    String dTel;
    String dTelPattern;

    Boolean dTelok;

    String dEmail;
    String dEmailPattern;

    Boolean dEmailok;

    BigDecimal shipcost;
    BigDecimalRange shipcostRange = new BigDecimalRange();

    Integer length;
    IntegerRange lengthRange = new IntegerRange();

    BigDecimal totalQuantity;
    BigDecimalRange totalQuantityRange = new BigDecimalRange();

    BigDecimal totalAmount;
    BigDecimalRange totalAmountRange = new BigDecimalRange();

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer value) {
        this.ownerUserId = value;
    }

    public IntegerRange getOwnerUserIdRange() {
        return ownerUserIdRange;
    }

    public void setOwnerUserIdRange(IntegerRange range) {
        this.ownerUserIdRange = range;
    }

    public Integer getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(Integer value) {
        this.accessMode = value;
    }

    public IntegerRange getAccessModeRange() {
        return accessModeRange;
    }

    public void setAccessModeRange(IntegerRange range) {
        this.accessModeRange = range;
    }

    public Integer getAcl() {
        return acl;
    }

    public void setAcl(Integer value) {
        this.acl = value;
    }

    public IntegerRange getAclRange() {
        return aclRange;
    }

    public void setAclRange(IntegerRange range) {
        this.aclRange = range;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer value) {
        this.priority = value;
    }

    public IntegerRange getPriorityRange() {
        return priorityRange;
    }

    public void setPriorityRange(IntegerRange range) {
        this.priorityRange = range;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp value) {
        this.creationDate = value;
    }

    public DateTimeRange getCreationDateRange() {
        return creationDateRange;
    }

    public void setCreationDateRange(DateTimeRange range) {
        this.creationDateRange = range;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp value) {
        this.lastModifiedDate = value;
    }

    public DateTimeRange getLastModifiedDateRange() {
        return lastModifiedDateRange;
    }

    public void setLastModifiedDateRange(DateTimeRange range) {
        this.lastModifiedDateRange = range;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer value) {
        this.version = value;
    }

    public IntegerRange getVersionRange() {
        return versionRange;
    }

    public void setVersionRange(IntegerRange range) {
        this.versionRange = range;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
        this.subject = value;
    }

    public String getSubjectPattern() {
        return subjectPattern;
    }

    public void setSubjectPattern(String value) {
        this.subjectPattern = value;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String value) {
        this.rawText = value;
    }

    public String getRawTextPattern() {
        return rawTextPattern;
    }

    public void setRawTextPattern(String value) {
        this.rawTextPattern = value;
    }

    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    public String getFormArgumentsPattern() {
        return formArgumentsPattern;
    }

    public void setFormArgumentsPattern(String value) {
        this.formArgumentsPattern = value;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Long getPrevId() {
        return prevId;
    }

    public void setPrevId(Long value) {
        this.prevId = value;
    }

    public LongRange getPrevIdRange() {
        return prevIdRange;
    }

    public void setPrevIdRange(LongRange range) {
        this.prevIdRange = range;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Long value) {
        this.salesOrderId = value;
    }

    public LongRange getSalesOrderIdRange() {
        return salesOrderIdRange;
    }

    public void setSalesOrderIdRange(LongRange range) {
        this.salesOrderIdRange = range;
    }

    public Long getStoreodrId() {
        return storeodrId;
    }

    public void setStoreodrId(Long value) {
        this.storeodrId = value;
    }

    public LongRange getStoreodrIdRange() {
        return storeodrIdRange;
    }

    public void setStoreodrIdRange(LongRange range) {
        this.storeodrIdRange = range;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer value) {
        this.shipperId = value;
    }

    public IntegerRange getShipperIdRange() {
        return shipperIdRange;
    }

    public void setShipperIdRange(IntegerRange range) {
        this.shipperIdRange = range;
    }

    public String getSAlias() {
        return sAlias;
    }

    public void setSAlias(String value) {
        this.sAlias = value;
    }

    public String getSAliasPattern() {
        return sAliasPattern;
    }

    public void setSAliasPattern(String value) {
        this.sAliasPattern = value;
    }

    public Object getSCtprops() {
        return sCtprops;
    }

    public void setSCtprops(Object value) {
        this.sCtprops = value;
    }

    public String getSAddress1() {
        return sAddress1;
    }

    public void setSAddress1(String value) {
        this.sAddress1 = value;
    }

    public String getSAddress1Pattern() {
        return sAddress1Pattern;
    }

    public void setSAddress1Pattern(String value) {
        this.sAddress1Pattern = value;
    }

    public String getSAddress2() {
        return sAddress2;
    }

    public void setSAddress2(String value) {
        this.sAddress2 = value;
    }

    public String getSAddress2Pattern() {
        return sAddress2Pattern;
    }

    public void setSAddress2Pattern(String value) {
        this.sAddress2Pattern = value;
    }

    public Integer getSZoneId() {
        return sZoneId;
    }

    public void setSZoneId(Integer value) {
        this.sZoneId = value;
    }

    public IntegerRange getSZoneIdRange() {
        return sZoneIdRange;
    }

    public void setSZoneIdRange(IntegerRange range) {
        this.sZoneIdRange = range;
    }

    public String getSTel() {
        return sTel;
    }

    public void setSTel(String value) {
        this.sTel = value;
    }

    public String getSTelPattern() {
        return sTelPattern;
    }

    public void setSTelPattern(String value) {
        this.sTelPattern = value;
    }

    public Boolean getSTelok() {
        return sTelok;
    }

    public void setSTelok(Boolean value) {
        this.sTelok = value;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String value) {
        this.sEmail = value;
    }

    public String getSEmailPattern() {
        return sEmailPattern;
    }

    public void setSEmailPattern(String value) {
        this.sEmailPattern = value;
    }

    public Boolean getSEmailok() {
        return sEmailok;
    }

    public void setSEmailok(Boolean value) {
        this.sEmailok = value;
    }

    public String getDAlias() {
        return dAlias;
    }

    public void setDAlias(String value) {
        this.dAlias = value;
    }

    public String getDAliasPattern() {
        return dAliasPattern;
    }

    public void setDAliasPattern(String value) {
        this.dAliasPattern = value;
    }

    public Object getDCtprops() {
        return dCtprops;
    }

    public void setDCtprops(Object value) {
        this.dCtprops = value;
    }

    public String getDAddress1() {
        return dAddress1;
    }

    public void setDAddress1(String value) {
        this.dAddress1 = value;
    }

    public String getDAddress1Pattern() {
        return dAddress1Pattern;
    }

    public void setDAddress1Pattern(String value) {
        this.dAddress1Pattern = value;
    }

    public String getDAddress2() {
        return dAddress2;
    }

    public void setDAddress2(String value) {
        this.dAddress2 = value;
    }

    public String getDAddress2Pattern() {
        return dAddress2Pattern;
    }

    public void setDAddress2Pattern(String value) {
        this.dAddress2Pattern = value;
    }

    public Integer getDZoneId() {
        return dZoneId;
    }

    public void setDZoneId(Integer value) {
        this.dZoneId = value;
    }

    public IntegerRange getDZoneIdRange() {
        return dZoneIdRange;
    }

    public void setDZoneIdRange(IntegerRange range) {
        this.dZoneIdRange = range;
    }

    public String getDTel() {
        return dTel;
    }

    public void setDTel(String value) {
        this.dTel = value;
    }

    public String getDTelPattern() {
        return dTelPattern;
    }

    public void setDTelPattern(String value) {
        this.dTelPattern = value;
    }

    public Boolean getDTelok() {
        return dTelok;
    }

    public void setDTelok(Boolean value) {
        this.dTelok = value;
    }

    public String getDEmail() {
        return dEmail;
    }

    public void setDEmail(String value) {
        this.dEmail = value;
    }

    public String getDEmailPattern() {
        return dEmailPattern;
    }

    public void setDEmailPattern(String value) {
        this.dEmailPattern = value;
    }

    public Boolean getDEmailok() {
        return dEmailok;
    }

    public void setDEmailok(Boolean value) {
        this.dEmailok = value;
    }

    public BigDecimal getShipcost() {
        return shipcost;
    }

    public void setShipcost(BigDecimal value) {
        this.shipcost = value;
    }

    public BigDecimalRange getShipcostRange() {
        return shipcostRange;
    }

    public void setShipcostRange(BigDecimalRange range) {
        this.shipcostRange = range;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer value) {
        this.length = value;
    }

    public IntegerRange getLengthRange() {
        return lengthRange;
    }

    public void setLengthRange(IntegerRange range) {
        this.lengthRange = range;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal value) {
        this.totalQuantity = value;
    }

    public BigDecimalRange getTotalQuantityRange() {
        return totalQuantityRange;
    }

    public void setTotalQuantityRange(BigDecimalRange range) {
        this.totalQuantityRange = range;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    public BigDecimalRange getTotalAmountRange() {
        return totalAmountRange;
    }

    public void setTotalAmountRange(BigDecimalRange range) {
        this.totalAmountRange = range;
    }

}
