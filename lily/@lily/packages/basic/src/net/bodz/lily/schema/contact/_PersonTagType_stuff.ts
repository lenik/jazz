import CoTag from "@skeljs/dba/src/net/bodz/lily/concrete/CoTag";

import { TypeParamType } from "../../meta/TypeParamType";
import _PersonTagType_stuff_Type from "./_PersonTagType_stuff_Type";

export class _PersonTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _PersonTagType_stuff_Type();


    constructor(o: any) {
        super(o);
    }
}

export default _PersonTagType_stuff;
