import { Moment } from 'moment';

import { integer } from '@skeljs/core/src/lang/type';
import { IdEntity } from '@skeljs/dba/src/net/bodz/lily/concrete/IdEntity';

import PartyType from "./PartyType";
import Contact from "./Contact";

export abstract class Party extends IdEntity<integer> {
    static TYPE = new PartyType();

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

export default Party;