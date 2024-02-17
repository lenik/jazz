
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _Badge_stuff_Type } from "./_Badge_stuff_Type";

export class _Badge_stuff extends CoEntity<Integer> {
    static TYPE = new _Badge_stuff_Type();

    id: int;
    expr?: string;
    val: int;
    levels?: int[];
    descend: boolean;
    transient_: boolean;
    indexed: boolean;

    constructor(o: any) {
        super(o);
    }
}
