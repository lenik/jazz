import type { double, int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type Uom from "./Uom";
import _Uom_stuff_TypeInfo from "./_Uom_stuff_TypeInfo";

export class _Uom_stuff extends CoEntity<int> {
    static _typeInfo: _Uom_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Uom_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    prop: string;
    scale: double;

    std?: Uom;
    stdId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Uom_stuff;
