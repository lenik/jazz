import { int } from 'skel01-core/src/lang/basetype';
import CoImaged from '../../src/net/bodz/lily/concrete/CoImaged';
import PartyTypeInfo from './PartyTypeInfo'
import Contact from './Contact';
import { LocalDate } from 'skel01-core/src/lang/time';

export abstract class Party extends CoImaged<int> {

    static _typeInfo: PartyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PartyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    category?: any
    birthday?: LocalDate

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
    }

}