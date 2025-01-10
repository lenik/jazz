import type { int } from "skel01-core/src/lang/basetype";

import Gender from "./Gender";
import Party from "./Party";
import type Person from "./Person";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class _Person_stuff extends Party {

    static _typeInfo: _Person_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Person_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    roleCount: int;
    employee: boolean;
    bankCount: int;
    gender?: Gender;
    pronoun?: string;
    sexualOrientation?: string;
    homeland?: string;
    passport?: string;
    ssn?: string;
    dln?: string;

    mother?: Person;
    motherId?: int;

    father?: Person;
    fatherId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Person_stuff;
