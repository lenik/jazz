import type { integer } from "@skeljs/core/src/lang/type";
import CoEntity from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntity";

import _Storage_stuff_Type from "./_Storage_stuff_Type";

export class _Storage_stuff extends CoEntity<integer> {
    static TYPE = new _Storage_stuff_Type();

    id: integer;
    name: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Storage_stuff;
