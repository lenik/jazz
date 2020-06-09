package net.bodz.violet.fab;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.mx.CoMessage;
import net.bodz.violet.plan.Plan;

@IdType(Long.class)
@Table(name = "fabodr")
public class FabOrder
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Plan plan;
    Organization customerOrg;
    Person customerPerson;
    Person clerk;

    SizedList<FabOrderItem> items = new SizedList<>();
    BigDecimal totalQuantity = BigDecimal.ZERO;
    BigDecimal totalAmount = BigDecimal.ZERO;

    Integer itemCount;
    Integer taskCount;
    Integer processCount;
    Integer trackCount;

    public FabOrder() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Organization getCustomerOrg() {
        return customerOrg;
    }

    public void setCustomerOrg(Organization customerOrg) {
        this.customerOrg = customerOrg;
    }

    public Person getCustomerPerson() {
        return customerPerson;
    }

    public void setCustomerPerson(Person customerPerson) {
        this.customerPerson = customerPerson;
    }

    public Person getClerk() {
        return clerk;
    }

    public void setClerk(Person clerk) {
        this.clerk = clerk;
    }

    public SizedList<FabOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<FabOrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer processCount) {
        this.processCount = processCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

}
