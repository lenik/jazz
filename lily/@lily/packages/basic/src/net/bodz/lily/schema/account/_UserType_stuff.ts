import type { int } from "skel01-core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class _UserType_stuff extends CoImaged<int> {

    static _typeInfo: _UserType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserType_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserType_stuff;
