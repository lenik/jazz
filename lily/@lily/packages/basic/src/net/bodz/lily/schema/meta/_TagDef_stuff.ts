import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type TagGroupDef from "./TagGroupDef";
import _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

export class _TagDef_stuff extends CoEntity<int> {
    static _typeInfo: _TagDef_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _TagDef_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;

    tagGroup: TagGroupDef;
    tagGroupId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _TagDef_stuff;
