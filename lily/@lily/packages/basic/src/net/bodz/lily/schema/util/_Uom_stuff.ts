import type { double, int } from "skel01-core/src/lang/basetype";

import CoCode from "../../concrete/CoCode";
import type Uom from "./Uom";

export class _Uom_stuff<this_t> extends CoCode<this_t> {

    property: string;
    scale: double;

    standard?: Uom;
    standardId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Uom_stuff;
