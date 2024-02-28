import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class _UserType_stuff extends CoEntity<int> {
    static _typeInfo: _UserType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _UserType_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    dummy?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserType_stuff;
