import type { int } from "skel01-core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class _Badge_stuff extends CoImaged<int> {

    static _typeInfo: _Badge_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Badge_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    expr?: string;
    val: int;
    levels?: int[];
    descend: boolean;
    transient_: boolean;
    indexed: boolean;

    constructor(o: any) {
        super(o);
    }
}

export default _Badge_stuff;
