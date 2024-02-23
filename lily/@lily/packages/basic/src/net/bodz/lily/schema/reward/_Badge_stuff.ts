import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _Badge_stuff_Type from "./_Badge_stuff_Type";

export class _Badge_stuff extends CoEntity<integer> {
    static TYPE = new _Badge_stuff_Type();

    id: integer;
    expr?: string;
    val: integer;
    levels?: int[];
    descend: boolean;
    transient_: boolean;
    indexed: boolean;
    image?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Badge_stuff;
