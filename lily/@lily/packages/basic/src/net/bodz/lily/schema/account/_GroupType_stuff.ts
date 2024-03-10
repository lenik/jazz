import type { int } from "@skeljs/core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _GroupType_stuff_TypeInfo from "./_GroupType_stuff_TypeInfo";

export class _GroupType_stuff extends CoImaged<int> {

    static _typeInfo: _GroupType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _GroupType_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _GroupType_stuff;
