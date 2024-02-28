import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type VAppCat from "./VAppCat";
import type VAppRequest from "./VAppRequest";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class _VApp_stuff extends CoEntity<int> {
    static _typeInfo: _VApp_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _VApp_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
    code?: string;
    secret: string;

    category?: VAppCat;
    categoryId?: int;

    req?: VAppRequest;
    reqId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VApp_stuff;
