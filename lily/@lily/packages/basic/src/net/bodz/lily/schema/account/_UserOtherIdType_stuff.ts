import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _UserOtherIdType_stuff_Type from "./_UserOtherIdType_stuff_Type";

export class _UserOtherIdType_stuff extends CoEntity<integer> {
    static TYPE = new _UserOtherIdType_stuff_Type();

    id: integer;
    dummy?: integer;

    constructor(o: any) {
        super(o);
    }
}

export default _UserOtherIdType_stuff;
