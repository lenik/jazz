package net.bodz.lily.schema.dao;

import java.sql.Timestamp;

import net.bodz.bas.t.range.DateTimeRange;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.lily.model.base.CoObjectMask;

public class _CategoryDefMask_stuff
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

    Integer schemaId;
    IntegerRange schemaIdRange = new IntegerRange();

    Integer parentId;
    IntegerRange parentIdRange = new IntegerRange();

    Integer depth;
    IntegerRange depthRange = new IntegerRange();

    Object properties;

    Integer objCount;
    IntegerRange objCountRange = new IntegerRange();

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

    public Integer getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(Integer value) {
        this.schemaId = value;
    }

    public IntegerRange getSchemaIdRange() {
        return schemaIdRange;
    }

    public void setSchemaIdRange(IntegerRange range) {
        this.schemaIdRange = range;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer value) {
        this.parentId = value;
    }

    public IntegerRange getParentIdRange() {
        return parentIdRange;
    }

    public void setParentIdRange(IntegerRange range) {
        this.parentIdRange = range;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer value) {
        this.depth = value;
    }

    public IntegerRange getDepthRange() {
        return depthRange;
    }

    public void setDepthRange(IntegerRange range) {
        this.depthRange = range;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object value) {
        this.properties = value;
    }

    public Integer getObjCount() {
        return objCount;
    }

    public void setObjCount(Integer value) {
        this.objCount = value;
    }

    public IntegerRange getObjCountRange() {
        return objCountRange;
    }

    public void setObjCountRange(IntegerRange range) {
        this.objCountRange = range;
    }

}
