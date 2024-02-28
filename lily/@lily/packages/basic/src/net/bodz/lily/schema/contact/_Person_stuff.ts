import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";

import Gender from "./Gender";
import Party from "./Party";
import type Person from "./Person";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class _Person_stuff extends Party {
    static _typeInfo: _Person_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Person_stuff_TypeInfo();
        return this._typeInfo;
    }

    properties?: JsonVariant;
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
