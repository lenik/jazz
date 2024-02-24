import type { integer } from "@skeljs/core/src/lang/type";

import CoEntity from "../../concrete/CoEntity";
import _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class _Policy_stuff extends CoEntity<integer> {
    static TYPE = new _Policy_stuff_TypeInfo();

    id: integer;
    name?: string;
    controlClass: string;
    methodName?: string;
    allowBits: integer;
    denyBits: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _Policy_stuff;
