import type { int } from "skel01-core/src/lang/basetype";
import CoImaged from "lily-basic/src/net/bodz/lily/concrete/CoImaged";

import type FabFn from "./FabFn";
import _FabFn_stuff_TypeInfo from "./_FabFn_stuff_TypeInfo";

export class _FabFn_stuff extends CoImaged<int> {

    static _typeInfo: _FabFn_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabFn_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code: string;
    depth: int;
    refCount: int;

    parent?: FabFn;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabFn_stuff;
