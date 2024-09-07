import { int } from '@skeljs/core/src/lang/basetype';
import CoMessage from '@lily/basic/src/net/bodz/lily/concrete/CoMessage';
import Contact from '@lily/basic/src/net/bodz/lily/schema/contact/Contact';

export abstract class AbstractTransportOrder<Id> extends CoMessage<Id> {

    src: Contact = new Contact();
    dst: Contact = new Contact();

    constructor(o: any) {
        super(o);
    }

}

export default AbstractTransportOrder;