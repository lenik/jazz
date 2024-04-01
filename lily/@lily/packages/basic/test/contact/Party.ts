import { int } from '@skeljs/core/src/lang/basetype';
import CoImaged from '../../src/net/bodz/lily/concrete/CoImaged';
import PartyTypeInfo from './PartyTypeInfo'
import Contact from './Contact';
import { LocalDate } from '@skeljs/core/src/lang/time';

export abstract class Party extends CoImaged<int> {

    static readonly TYPE = new PartyTypeInfo();

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