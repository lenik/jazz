import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import _Badge_stuff_TypeInfo from "./_Badge_stuff_TypeInfo";

export class _Badge_stuff extends CoEntity<int> {
    static _typeInfo: _Badge_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Badge_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    expr?: string;
    val: int;
    levels?: int[];
    descend: boolean;
    transient_: boolean;
    indexed: boolean;
    image?: string;

    constructor(o: any) {
        super(o);
    }
}

export default _Badge_stuff;
