import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _Policy_stuff_Type from "./_Policy_stuff_Type";

export class _Policy_stuff extends CoEntity<integer> {
    static TYPE = new _Policy_stuff_Type();

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
