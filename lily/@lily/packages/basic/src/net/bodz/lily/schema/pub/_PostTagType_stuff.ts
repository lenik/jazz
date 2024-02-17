
import type { CoTag } from "@skeljs/dba/src/net/bodz/lily/concrete/CoTag";

import type { TypeParamType } from "../../meta/TypeParamType";
import type { _PostTagType_stuff_Type } from "./_PostTagType_stuff_Type";

export class _PostTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _PostTagType_stuff_Type();


    constructor(o: any) {
        super(o);
    }
}
