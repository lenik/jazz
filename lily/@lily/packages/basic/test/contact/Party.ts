import { Moment } from 'moment-timezone';
import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from '../../src/net/bodz/lily/concrete/IdEntity';
import PartyTypeInfo from './PartyTypeInfo'
import Contact from './Contact';

export abstract class Party extends IdEntity<int> {

    static _typeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PartyTypeInfo();
        return this._typeInfo;
    }

    category?: any
    birthday?: Moment

    locale: string
    timeZoneId: string

    peer: boolean = false
    custoemr: boolean = false
    supplier: boolean = false

    tags: string[]

    subject?: string
    contacts: Contact[]

    bank?: string
    account?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
