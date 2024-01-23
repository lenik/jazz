package net.bodz.violet.tran;

import java.math.BigDecimal;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.geo.Zone;
import net.bodz.lily.geo.dao.ZoneMapper;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.dao.SalesOrderMapper;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.dao.StoreOrderMapper;
import net.bodz.violet.tran.dao.TransportCategoryMapper;
import net.bodz.violet.tran.dao.TransportOrderMapper;
import net.bodz.violet.tran.dao.TransportPhaseMapper;

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
    public Zone dZone;
    public Zone sZone;
    public StoreOrder storeodr;

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
        a.setSAlias("Uoi oai ht? u, Eian, a_uubdasou");
        a.setSAddress1("Hohis; Ac, dubeo&vdcq n_elgeg");
        a.setSAddress2("Anui xs_hu rdio; e. Ro-qg, jiw? z.");
        a.setSTel("jf obriwy!");
        a.setSTelok(true);
        a.setSEmail("Uqivua?");
        a.setSEmailok(true);
        a.setDAlias("Ytm; isaqyuj i qnydie@muj");
        a.setDAddress1("Huia&uuuw, itkc! rysf uuumm ti hxioo");
        a.setDAddress2("Q seqd; qboikzvq*ti_Gwkg unoce*e. nat*i_uic! ca. x gkeu@ri. t");
        a.setDTel("Ruewh_jhee.");
        a.setDTelok(true);
        a.setDEmail("pej.");
        a.setDEmailok(true);
        a.setShipcost(new BigDecimal(""));
        a.setLength(478514027);
        a.setTotalQuantity(new BigDecimal("895237"));
        a.setTotalAmount(new BigDecimal("62"));
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
        this.dZone = picker.pickAny(ZoneMapper.class, "zone");
        this.sZone = picker.pickAny(ZoneMapper.class, "zone");
        this.storeodr = picker.pickAny(StoreOrderMapper.class, "storeodr");
        return this;
    }

    @Override
    public TransportOrder buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
