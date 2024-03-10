import type { int } from "@skeljs/core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class _ApiType_stuff extends CoImaged<int> {

    static _typeInfo: _ApiType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _ApiType_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    uom: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ApiType_stuff;
