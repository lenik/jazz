
import type { CoCategory } from "@skeljs/dba/src/net/bodz/lily/concrete/CoCategory";

import type { TypeParamType } from "../../meta/TypeParamType";
import type { _PartyCategory_stuff_Type } from "./_PartyCategory_stuff_Type";

export class _PartyCategory_stuff<this_t> extends CoCategory<this_t, integer> {
    static TYPE = new _PartyCategory_stuff_Type();

    name?: string;

    constructor(o: any) {
        super(o);
    }
}
