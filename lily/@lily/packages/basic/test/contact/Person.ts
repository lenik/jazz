import type { int } from "@skeljs/core/src/lang/basetype";
import { Party } from './Party';
import PersonTypeInfo from './PersonTypeInfo';
import Attachment from "@skeljs/core/src/net/bodz/lily/entity/Attachment";

export class Person extends Party {

    static readonly TYPE = new PersonTypeInfo();

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

    get images() {
        return this.properties as Attachment[]
    }

    get image() {
        if (this.images == null) return undefined;
        else return this.images[0];
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
