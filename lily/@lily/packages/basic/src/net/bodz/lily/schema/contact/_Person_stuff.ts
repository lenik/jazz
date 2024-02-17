
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Party } from "./Party";
import type { Person } from "./Person";
import type { _Person_stuff_Type } from "./_Person_stuff_Type";

export class _Person_stuff extends Party {
    static TYPE = new _Person_stuff_Type();

    roleCount: int;
    employee: boolean;
    bankCount: int;
    gender?: string;
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
