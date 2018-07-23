package net.bodz.violet.asset;

import java.math.BigDecimal;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.lily.model.base.security.Group;
import net.bodz.lily.model.base.security.User;
import net.bodz.lily.model.contact.OrgUnit;
import net.bodz.lily.model.contact.Organization;
import net.bodz.lily.model.contact.Person;
import net.bodz.violet.art.Artifact;
import net.bodz.violet.store.Region;

/**
 * 资产
 */
@Table(name = "asset")
@IdType(Long.class)
public class Asset
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    Artifact artifact;
    Region region;
    // Batch complex
    BigDecimal quantity;
    Long serial;
    DateTime expire;

    User user;
    Group group;
    Organization org;
    OrgUnit orgUnit;
    Person person;

    public Asset() {
    }

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public DateTime getExpire() {
        return expire;
    }

    public void setExpire(DateTime expire) {
        this.expire = expire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
