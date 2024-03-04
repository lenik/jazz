import type { int } from "@skeljs/core/src/lang/basetype";
import { Party } from './Party';
import PersonTypeInfo from './PersonType';

export class Person extends Party {

    static _typeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PersonTypeInfo();
        return this._typeInfo;
    }

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
