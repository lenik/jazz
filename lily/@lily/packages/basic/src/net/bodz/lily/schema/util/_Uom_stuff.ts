import type { double, integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Uom from "./Uom";
import _Uom_stuff_Type from "./_Uom_stuff_Type";

export class _Uom_stuff extends CoEntity<integer> {
    static TYPE = new _Uom_stuff_Type();

    id: integer;
    code?: string;
    prop: string;
    scale: double;

    std?: Uom;
    stdId?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Uom_stuff;
