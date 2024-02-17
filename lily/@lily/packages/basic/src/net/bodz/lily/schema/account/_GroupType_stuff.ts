
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _GroupType_stuff_Type } from "./_GroupType_stuff_Type";

export class _GroupType_stuff extends CoEntity<Integer> {
    static TYPE = new _GroupType_stuff_Type();

    id: int;
    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}
