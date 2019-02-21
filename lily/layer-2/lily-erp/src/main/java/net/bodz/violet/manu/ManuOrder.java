package net.bodz.violet.manu;

import java.math.BigDecimal;

import javax.persistence.Table;

import net.bodz.lily.contact.Person;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.entity.SizedList;
import net.bodz.lily.model.mx.CoMessage;
import net.bodz.violet.plan.Plan;

import user.book.Org;

@IdType(Long.class)
@Table(name = "manuodr")
public class ManuOrder
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    Plan plan;
    Org customerOrg;
    Person customerPerson;
    Person clerk;

    SizedList<ManuOrderItem> items = new SizedList<>();
    BigDecimal totalQuantity = BigDecimal.ZERO;
    BigDecimal totalAmount = BigDecimal.ZERO;

    Integer itemCount;
    Integer taskCount;
    Integer processCount;
    Integer trackCount;

    public ManuOrder() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Org getCustomerOrg() {
        return customerOrg;
    }

    public void setCustomerOrg(Org customerOrg) {
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

    public SizedList<ManuOrderItem> getItems() {
        return items;
    }

    public void setItems(SizedList<ManuOrderItem> items) {
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
