
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _Storage_stuff_Type } from "./_Storage_stuff_Type";

export class _Storage_stuff extends CoEntity<Integer> {
    static TYPE = new _Storage_stuff_Type();

    id: int;
    name: string;

    constructor(o: any) {
        super(o);
    }
}
