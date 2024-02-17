
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _ApiType_stuff_Type } from "./_ApiType_stuff_Type";

export class _ApiType_stuff extends CoEntity<Integer> {
    static TYPE = new _ApiType_stuff_Type();

    id: int;
    code?: string;
    uom: string;

    constructor(o: any) {
        super(o);
    }
}
