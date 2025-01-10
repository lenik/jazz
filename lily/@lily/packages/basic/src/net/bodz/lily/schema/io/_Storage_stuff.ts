import type { int } from "skel01-core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class _Storage_stuff extends CoImaged<int> {

    static _typeInfo: _Storage_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Storage_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    name: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Storage_stuff;
