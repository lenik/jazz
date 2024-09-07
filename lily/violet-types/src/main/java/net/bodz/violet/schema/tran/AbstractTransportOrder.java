package net.bodz.violet.schema.tran;

import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;
import net.bodz.lily.schema.contact.Contact;

@TypeParameters({ TypeParamType.ID_TYPE })
public abstract class AbstractTransportOrder<Id>
        extends CoMessage<Id> {

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
