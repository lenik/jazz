
import type { CoEntity } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { _Policy_stuff_Type } from "./_Policy_stuff_Type";

export class _Policy_stuff extends CoEntity<Integer> {
    static TYPE = new _Policy_stuff_Type();

    id: int;
    name?: string;
    controlClass: string;
    methodName?: string;
    allowBits: int;
    denyBits: int;

    constructor(o: any) {
        super(o);
    }
}
