import type { int } from "skel01-core/src/lang/basetype";

import CoPrincipal from "../../concrete/CoPrincipal";
import type Group from "./Group";
import type GroupType from "./GroupType";
import _Group_stuff_TypeInfo from "./_Group_stuff_TypeInfo";

export class _Group_stuff extends CoPrincipal {

    static _typeInfo: _Group_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Group_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    parent?: Group;
    parentId?: int;

    type: GroupType;
    typeId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Group_stuff;
