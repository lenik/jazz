package net.bodz.violet.schema.tran;

import java.math.BigDecimal;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.Contact;
import net.bodz.lily.schema.contact.ContactSamples;
import net.bodz.lily.schema.contact.Organization;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.dao.FormDefMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.shop.SalesOrder;
import net.bodz.violet.schema.shop.dao.SalesOrderMapper;
import net.bodz.violet.schema.store.StoreOrder;
import net.bodz.violet.schema.store.dao.StoreOrderMapper;
import net.bodz.violet.schema.tran.dao.TransportCategoryMapper;
import net.bodz.violet.schema.tran.dao.TransportOrderMapper;
import net.bodz.violet.schema.tran.dao.TransportPhaseMapper;

public class TransportOrderSamples
        extends TestSampleBuilder {

    public User op;
    public Group ownerGroup;
    public User ownerUser;
    public TransportOrder prev;
    public FormDef form;
    public Organization shipper;
    public TransportCategory category;
    public SalesOrder salesOrder;
    public TransportPhase phase;
    public StoreOrder storeodr;

    public Contact dst;
    public Contact src;

    @Override
    public TransportOrder build()
            throws Exception {
        TransportOrder a = new TransportOrder();
        a.setOp(op);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setPrev(prev);
        a.setForm(form);
        a.setShipper(shipper);
        a.setCategory(category);
        a.setSalesOrder(salesOrder);
        a.setPhase(phase);
        a.setStoreodr(storeodr);
        a.setFormArguments("jjaq-giu! Eukuc gfpc w, ai fl*vsa upkzf? gpv'aln, wupej, ov'muqo uuu! oobg, ghaw_ao Xhk uboe ii iis, xkhua u@aia; xuq. Iua#htuze e eaii, myk, qazn#lp oobe; eaci nae y aop gw bkeupaa vu, o&uwio_og zoze uomz if Yobj'roue, quiun, ioi uga-r zhihee wp i*iyuh c; ioam uayei ieionr@joi. qfoiar a@o, ezn Ani&awwlv ieax, ol iuvzv. muaa; v, ihlkuy luyauu lh gfufaos, vuge Uib Huzxr yx.");
        a.setShipcost(new BigDecimal("429055513.89"));
        a.setLength(478514027);
        a.setTotalQuantity(new BigDecimal("895237"));
        a.setTotalAmount(new BigDecimal("62"));
        a.setDst(new ContactSamples().build());
        a.setSrc(new ContactSamples().build());
        return a;
    }

    @Override
    public TransportOrderSamples wireAny(IRandomPicker picker) {
        this.op = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.prev = picker.pickAny(TransportOrderMapper.class, "tranodr");
        this.form = picker.pickAny(FormDefMapper.class, "_form");
        this.shipper = picker.pickAny(OrganizationMapper.class, "org");
        this.category = picker.pickAny(TransportCategoryMapper.class, "trancat");
        this.salesOrder = picker.pickAny(SalesOrderMapper.class, "saleodr");
        this.phase = picker.pickAny(TransportPhaseMapper.class, "tranphase");
        this.storeodr = picker.pickAny(StoreOrderMapper.class, "storeodr");
        return this;
    }

    @Override
    public TransportOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
