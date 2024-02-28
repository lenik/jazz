import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _Policy_stuff_TypeInfo from "./_Policy_stuff_TypeInfo";

export class _Policy_stuff extends CoEntity<int> {
    static _typeInfo: _Policy_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Policy_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    name?: string;
    controlClass: string;
    methodName?: string;
    allowBits: int;
    denyBits: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Policy_stuff;
