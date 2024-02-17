
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _UserType_stuff_Type } from "./_UserType_stuff_Type";

export class _UserType_stuff extends CoEntity<Integer> {
    static TYPE = new _UserType_stuff_Type();

    id: int;
    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}
