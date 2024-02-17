
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _UserOtherIdType_stuff_Type } from "./_UserOtherIdType_stuff_Type";

export class _UserOtherIdType_stuff extends CoEntity<Integer> {
    static TYPE = new _UserOtherIdType_stuff_Type();

    id: int;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}
