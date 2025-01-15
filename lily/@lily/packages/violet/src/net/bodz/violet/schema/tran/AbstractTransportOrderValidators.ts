import CoMessageValidators from 'lily-basic/src/net/bodz/lily/concrete/CoMessageValidators';
import AbstractTransportOrderTypeInfo from './AbstractTransportOrderTypeInfo';
import Contact from 'lily-basic/src/net/bodz/lily/schema/contact/Contact';

export class AbstractTransportOrderValidators extends CoMessageValidators {

    constructor(type: AbstractTransportOrderTypeInfo) {
        super(type);
    }

    validateSrc(val: Contact) {
    }

    validateDst(val: Contact) {
    }

}

export default AbstractTransportOrderValidators;