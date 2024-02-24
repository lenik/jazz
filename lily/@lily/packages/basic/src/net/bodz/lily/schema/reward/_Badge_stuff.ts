import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class _Badge_stuff extends CoEntity<integer> {
    static TYPE = new _Badge_stuff_TypeInfo();

    id: integer;
    expr?: string;
    val: integer;
    levels?: integer[];
    descend: boolean;
    transient_: boolean;
    indexed: boolean;
    image?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Badge_stuff;
