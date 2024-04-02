import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from '../../src/net/bodz/lily/concrete/IdEntity';
import ContactTypeInfo from './ContactTypeInfo';

export class Contact extends IdEntity<int> {

    static _typeInfo: ContactTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ContactTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    org: any
    orgUnit: any
    person: any

    rename?: string
    usage?: string

    zone: any
    zoneId?: int

    country?: string
    r1?: string
    r2?: string
    r3?: string
    r4?: string
    address1?: string
    address2?: string
    postalCode?: string

    tel?: string
    mobile?: string
    fax?: string
    email?: string
    web?: string
    qq?: string
    wechat?: string

    constructor(o?: any) {
        super(o);
    }
}

export default Contact;
