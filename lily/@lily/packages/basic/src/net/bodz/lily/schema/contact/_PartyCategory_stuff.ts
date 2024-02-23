import type { integer } from "@skeljs/core/src/lang/type";
import CoCategory from "@skeljs/dba/src/net/bodz/lily/concrete/CoCategory";

import { TypeParamType } from "../../meta/TypeParamType";
import _PartyCategory_stuff_Type from "./_PartyCategory_stuff_Type";

export class _PartyCategory_stuff<this_t> extends CoCategory<this_t, integer> {
    static TYPE = new _PartyCategory_stuff_Type();

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _PartyCategory_stuff;
