import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ZoneCategory from "./ZoneCategory";
import _ZoneCategory_stuff_TypeInfo from "./_ZoneCategory_stuff_TypeInfo";

export class _ZoneCategory_stuff extends CoEntity<int> {
    static _typeInfo: _ZoneCategory_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ZoneCategory_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: ZoneCategory;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ZoneCategory_stuff;
