import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ExternalSite from "./ExternalSite";
import _ExternalSite_stuff_TypeInfo from "./_ExternalSite_stuff_TypeInfo";

export class _ExternalSite_stuff extends CoEntity<int> {
    static _typeInfo: _ExternalSite_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ExternalSite_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    depth: int;
    urlfmt?: string;
    bonus: int;
    count: int;

    parent?: ExternalSite;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _ExternalSite_stuff;
