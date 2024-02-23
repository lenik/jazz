import type { double, integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import Uom from "./Uom";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class _Uom_stuff extends CoEntity<integer> {
    static TYPE = new _Uom_stuff_TypeInfo();

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
