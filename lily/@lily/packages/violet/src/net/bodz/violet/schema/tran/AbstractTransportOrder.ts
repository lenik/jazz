import { int } from 'skel01-core/src/lang/basetype';
import CoMessage from 'lily-basic/src/net/bodz/lily/concrete/CoMessage';
import AbstractTransportOrderTypeInfo from './AbstractTransportOrderTypeInfo';
import Contact from 'lily-basic/src/net/bodz/lily/schema/contact/Contact';

export abstract class AbstractTransportOrder<Id> extends CoMessage<Id> {

    static readonly TYPE = AbstractTransportOrderTypeInfo.INSTANCE;

    src: Contact = new Contact();
    dst: Contact = new Contact();

    constructor(o: any) {
        super(o);
    }

}

export default AbstractTransportOrder;