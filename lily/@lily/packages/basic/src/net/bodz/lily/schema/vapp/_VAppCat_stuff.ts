import type { int } from "@skeljs/core/src/lang/basetype";

import CoCategory from "../../concrete/CoCategory";
import _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class _VAppCat_stuff<this_t> extends CoCategory<this_t, int> {
    static _typeInfo: _VAppCat_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _VAppCat_stuff_TypeInfo();
        return this._typeInfo;
    }

    name?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppCat_stuff;
