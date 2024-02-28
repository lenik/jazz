import type { int } from "@skeljs/core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";
import _PartyCategory_stuff_TypeInfo from "./_PartyCategory_stuff_TypeInfo";

export class _PartyCategory_stuff<this_t> extends CoCategory<this_t, int> {
    static _typeInfo: _PartyCategory_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PartyCategory_stuff_TypeInfo();
        return this._typeInfo;
    }

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _PartyCategory_stuff;
