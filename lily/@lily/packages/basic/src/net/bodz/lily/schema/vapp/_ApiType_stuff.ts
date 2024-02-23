import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _ApiType_stuff_Type from "./_ApiType_stuff_Type";

export class _ApiType_stuff extends CoEntity<integer> {
    static TYPE = new _ApiType_stuff_Type();

    id: integer;
    code?: string;
    uom: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ApiType_stuff;
