import type { int } from "@skeljs/core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class _UserOtherIdType_stuff extends CoImaged<int> {

    static _typeInfo: _UserOtherIdType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserOtherIdType_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserOtherIdType_stuff;
