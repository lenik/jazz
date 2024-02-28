import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type PostCategory from "./PostCategory";
import _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class _PostCategory_stuff extends CoEntity<int> {
    static _typeInfo: _PostCategory_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PostCategory_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: PostCategory;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PostCategory_stuff;
