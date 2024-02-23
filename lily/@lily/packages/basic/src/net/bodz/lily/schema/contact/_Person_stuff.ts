import type { char, integer } from "@skeljs/core/src/lang/type";

import Party from "./Party";
import Person from "./Person";
import _Person_stuff_TypeInfo from "./_Person_stuff_TypeInfo";

export class _Person_stuff extends Party {
    static TYPE = new _Person_stuff_TypeInfo();

    properties?: any;
    roleCount: integer;
    employee: boolean;
    bankCount: integer;
    gender?: char;
    pronoun?: string;
    sexualOrientation?: string;
    homeland?: string;
    passport?: string;
    ssn?: string;
    dln?: string;

    mother?: Person;
    motherId?: integer;

    father?: Person;
    fatherId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Person_stuff;
