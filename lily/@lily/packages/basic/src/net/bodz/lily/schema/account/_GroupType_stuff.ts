import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class _GroupType_stuff extends CoEntity<integer> {
    static TYPE = new _GroupType_stuff_TypeInfo();

    id: integer;
    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _GroupType_stuff;
