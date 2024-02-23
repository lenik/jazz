import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class _ApiType_stuff extends CoEntity<integer> {
    static TYPE = new _ApiType_stuff_TypeInfo();

    id: integer;
    code?: string;
    uom: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ApiType_stuff;
