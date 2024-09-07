package net.bodz.violet.schema.tran;

import net.bodz.bas.meta.decl.TsTyped;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.schema.contact.Contact;

//@FieldGroupVue
@TsTyped
public abstract class AbstractTransportOrder
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    protected Contact src = new Contact();
    protected Contact dst = new Contact();

    public Contact getSrc() {
        return src;
    }

    public void setSrc(Contact src) {
        this.src = src;
    }

    public Contact getDst() {
        return dst;
    }

    public void setDst(Contact dst) {
        this.dst = dst;
    }

}
