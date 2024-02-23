import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _UserType_stuff_Type from "./_UserType_stuff_Type";

export class _UserType_stuff extends CoEntity<integer> {
    static TYPE = new _UserType_stuff_Type();

    id: integer;
    name?: string;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserType_stuff;
