package net.bodz.lily.security.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;

public class _GroupMask_stuff
        extends CoPrincipalMask {

    Integer id;
    IntegerRange idRange = new IntegerRange();

    /** The group name (unique) */
    String uniqName;
    String uniqNamePattern;

    /** Group type like normal-group, role-group, etc. */
    Integer typeId;
    IntegerRange typeIdRange = new IntegerRange();

    Integer priority;
    IntegerRange priorityRange = new IntegerRange();

    Timestamp creationDate;
    DateTimeRange creationDateRange = new DateTimeRange();

    Timestamp lastModifiedDate;
    DateTimeRange lastModifiedDateRange = new DateTimeRange();

    Integer version;
    IntegerRange versionRange = new IntegerRange();

    Object properties;

    /** The parent group. must be acyclic */
    Integer parentId;
    IntegerRange parentIdRange = new IntegerRange();

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    @Override
    public IntegerRange getIdRange() {
        return idRange;
    }

    @Override
    public void setIdRange(IntegerRange range) {
        this.idRange = range;
    }

    /** The group name (unique) */
    public String getUniqName() {
        return uniqName;
    }

    /** The group name (unique) */
    public void setUniqName(String value) {
        this.uniqName = value;
    }

    public String getUniqNamePattern() {
        return uniqNamePattern;
    }

    public void setUniqNamePattern(String value) {
        this.uniqNamePattern = value;
    }

    /** Group type like normal-group, role-group, etc. */
    public Integer getTypeId() {
        return typeId;
    }

    /** Group type like normal-group, role-group, etc. */
    public void setTypeId(Integer value) {
        this.typeId = value;
    }

    public IntegerRange getTypeIdRange() {
        return typeIdRange;
    }

    public void setTypeIdRange(IntegerRange range) {
        this.typeIdRange = range;
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

    /** The parent group. must be acyclic */
    public Integer getParentId() {
        return parentId;
    }

    /** The parent group. must be acyclic */
    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public IntegerRange getParentIdRange() {
        return parentIdRange;
    }

    public void setParentIdRange(IntegerRange range) {
        this.parentIdRange = range;
    }

}