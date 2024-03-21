import type { int } from "@skeljs/core/src/lang/basetype";
import { Party } from './Party';
import PersonTypeInfo from './PersonTypeInfo';
import Attachment from "@skeljs/core/src/net/bodz/lily/entity/Attachment";

export class Person extends Party {

    static readonly TYPE = PersonTypeInfo.INSTANCE;

    gender?: string

    father?: Person
    fatherId?: int
    mother?: Person
    motherId?: int

    roleCount?: int
    // roles: string[]

    employee: boolean
    bankCount?: int
    homeland?: string
    passport?: string
    ssn?: string
    dln?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
