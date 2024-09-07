import { integer } from "@skeljs/core/src/lang/type";
import { IdEntity } from '../../concrete/IdEntity';

import ContactTypeInfo from "./ContactTypeInfo";

export class Contact extends IdEntity<integer> {
    static TYPE = new ContactTypeInfo();

    org: any
    orgUnit: any
    person: any

    rename?: string
    usage?: string

    zone: any
    zoneId?: integer

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

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Contact;