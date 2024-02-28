import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type VAppCat from "./VAppCat";
import _VAppCat_stuff_TypeInfo from "./_VAppCat_stuff_TypeInfo";

export class _VAppCat_stuff extends CoEntity<int> {
    static _typeInfo: _VAppCat_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _VAppCat_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: VAppCat;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppCat_stuff;
