import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _Storage_stuff_TypeInfo from "./_Storage_stuff_TypeInfo";

export class _Storage_stuff extends CoEntity<int> {
    static _typeInfo: _Storage_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Storage_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Storage_stuff;
