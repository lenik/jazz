
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";
import type { integer } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { Uom } from "./Uom";
import type { _Uom_stuff_Type } from "./_Uom_stuff_Type";

export class _Uom_stuff extends CoEntity<Integer> {
    static TYPE = new _Uom_stuff_Type();

    id: int;
    code?: string;
    prop: string;
    scale: double;

    std?: Uom;
    stdId?: integer;

    constructor(o: any) {
        super(o);
    }
}
