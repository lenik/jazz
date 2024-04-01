import { int } from "@skeljs/core/src/lang/basetype";
import { IdEntity } from '../../concrete/IdEntity';

import ContactTypeInfo from "./ContactTypeInfo";

export class Contact extends IdEntity<int> {

    static _typeInfo: ContactTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ContactTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    org: any
    private _orgId?: int
    get orgId() {
        return this.org != null ? this.org.id : this._orgId;
    }
    set orgId(val: int | undefined) {
        this._orgId = val;
    }

    orgUnit: any
    private _orgUnitId?: int
    get orgUnitId() {
        return this.orgUnit != null ? this.orgUnit.id : this._orgUnitId;
    }
    set orgUnitId(val: int | undefined) {
        this._orgUnitId = val;
    }

    person: any
    private _personId?: int
    get personId() {
        return this.person != null ? this.person.id : this._personId;
    }
    set personId(val: int | undefined) {
        this._personId = val;
    }

    rename?: string
    usage?: string

    zone: any
    private _zoneId?: int
    get zoneId() {
        return this.zone != null ? this.zone.id : this._zoneId;
    }
    set zoneId(val: int | undefined) {
        this._zoneId = val;
    }

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