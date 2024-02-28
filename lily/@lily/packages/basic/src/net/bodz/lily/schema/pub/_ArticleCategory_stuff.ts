import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ArticleCategory from "./ArticleCategory";
import _ArticleCategory_stuff_TypeInfo from "./_ArticleCategory_stuff_TypeInfo";

export class _ArticleCategory_stuff extends CoEntity<int> {
    static _typeInfo: _ArticleCategory_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ArticleCategory_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    depth: int;
    refCount: int;

    parent?: ArticleCategory;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ArticleCategory_stuff;
