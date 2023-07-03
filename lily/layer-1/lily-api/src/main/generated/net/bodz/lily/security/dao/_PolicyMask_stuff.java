package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _PolicyMask_stuff
        extends CoObjectMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    /** The control class */
    String controlClass;
    String controlClassPattern;

    /** The method name */
    String methodName;
    String methodNamePattern;

    /** allow */
    Integer allowBits;
    IntegerRange allowBitsRange = new IntegerRange();

    /** deny */
    Integer denyBits;
    IntegerRange denyBitsRange = new IntegerRange();

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public IntegerRange getIdRange() {
        return idRange;
    }

    public void setIdRange(IntegerRange range) {
        this.idRange = range;
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

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    /** The control class */
    public String getControlClass() {
        return controlClass;
    }

    /** The control class */
    public void setControlClass(String value) {
        this.controlClass = value;
    }

    public String getControlClassPattern() {
        return controlClassPattern;
    }

    public void setControlClassPattern(String value) {
        this.controlClassPattern = value;
    }

    /** The method name */
    public String getMethodName() {
        return methodName;
    }

    /** The method name */
    public void setMethodName(String value) {
        this.methodName = value;
    }

    public String getMethodNamePattern() {
        return methodNamePattern;
    }

    public void setMethodNamePattern(String value) {
        this.methodNamePattern = value;
    }

    /** allow */
    public Integer getAllowBits() {
        return allowBits;
    }

    /** allow */
    public void setAllowBits(Integer value) {
        this.allowBits = value;
    }

    public IntegerRange getAllowBitsRange() {
        return allowBitsRange;
    }

    public void setAllowBitsRange(IntegerRange range) {
        this.allowBitsRange = range;
    }

    /** deny */
    public Integer getDenyBits() {
        return denyBits;
    }

    /** deny */
    public void setDenyBits(Integer value) {
        this.denyBits = value;
    }

    public IntegerRange getDenyBitsRange() {
        return denyBitsRange;
    }

    public void setDenyBitsRange(IntegerRange range) {
        this.denyBitsRange = range;
    }

}
