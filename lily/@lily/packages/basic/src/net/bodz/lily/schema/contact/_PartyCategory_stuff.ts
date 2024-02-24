import type { integer } from "@skeljs/core/src/lang/type";

import CoCategory from "../../concrete/CoCategory";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class _PartyCategory_stuff<this_t> extends CoCategory<this_t, integer> {
    static TYPE = new _PartyCategory_stuff_TypeInfo();

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _PartyCategory_stuff;
