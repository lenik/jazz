import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _ApiType_stuff_TypeInfo from "./_ApiType_stuff_TypeInfo";

export class _ApiType_stuff extends CoEntity<int> {
    static _typeInfo: _ApiType_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _ApiType_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    uom: string;

    constructor(o: any) {
        super(o);
    }
}

export default _ApiType_stuff;
