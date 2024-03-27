package net.bodz.violet.schema.shop;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.Person;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.plan.Plan;
import net.bodz.violet.schema.plan.dao.PlanMapper;
import net.bodz.violet.schema.shop.dao.SalesCategoryMapper;
import net.bodz.violet.schema.shop.dao.SalesOrderMapper;
import net.bodz.violet.schema.shop.dao.SalesPhaseMapper;

public class SalesOrderSamples
        extends TestSampleBuilder {

    public Person customer;
    public FormDef form;
    public SalesPhase phase;
    public Organization customerOrg;
    public Group ownerGroup;
    public User ownerUser;
    public SalesOrder previousOrder;
    public User op;
    public Plan plan;
    public SalesCategory category;

    @Override
    public SalesOrder build()
            throws Exception {
        SalesOrder a = new SalesOrder();
        a.setCustomer(customer);
        a.setForm(form);
        a.setPhase(phase);
        a.setCustomerOrg(customerOrg);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setPreviousOrder(previousOrder);
        a.setOp(op);
        a.setPlan(plan);
        a.setCategory(category);
        a.setFormArguments("gq*r ai*vke Ee? zn. pesc aioc l. uju, A, uw&au&Hlkc#v#hymu? z, oax; ojawc xui ya aaoiom, vicy zrh'uca ua");
        a.setLength(373315350);
        a.setTotalQuantity(new BigDecimal("13495435661584393"));
        a.setTotalAmount(new BigDecimal("425516256509965009.18"));
        return a;
    }

    @Override
    public SalesOrderSamples wireAny(IRandomPicker picker) {
        this.customer = picker.pickAny(PersonMapper.class, "person");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.phase = picker.pickAny(SalesPhaseMapper.class, "salephase");
        this.customerOrg = picker.pickAny(OrganizationMapper.class, "org");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.previousOrder = picker.pickAny(SalesOrderMapper.class, "saleodr");
        this.op = picker.pickAny(UserMapper.class, "user");
        this.plan = picker.pickAny(PlanMapper.class, "plan");
        this.category = picker.pickAny(SalesCategoryMapper.class, "salecat");
        return this;
    }

    @Override
    public SalesOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
