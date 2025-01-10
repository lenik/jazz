import type { int } from "skel01-core/src/lang/basetype";
import { Party } from './Party';
import PersonTypeInfo from './PersonTypeInfo';

export class Person extends Party {

    static _typeInfo: PersonTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PersonTypeInfo.INSTANCE;
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

    constructor(o?: any) {
        super(o);
    }
}

export default Person;
