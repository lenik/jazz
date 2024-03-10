import type { int } from "@skeljs/core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";

import _Shop_stuff_TypeInfo from "./_Shop_stuff_TypeInfo";

export class _Shop_stuff extends CoImaged<int> {

    static _typeInfo: _Shop_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Shop_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    hydm?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Shop_stuff;
