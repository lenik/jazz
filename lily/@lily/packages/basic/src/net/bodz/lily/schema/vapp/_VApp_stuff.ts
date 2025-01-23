import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type VAppCategory from "./VAppCategory";
import type VAppRequest from "./VAppRequest";
import _VApp_stuff_TypeInfo from "./_VApp_stuff_TypeInfo";

export class _VApp_stuff extends IdEntity<int> {

    static _typeInfo: _VApp_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VApp_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    properties?: JsonVariant;
    files?: JsonVariant;
    secret: string;

    category?: VAppCategory;
    categoryId?: int;

    req?: VAppRequest;
    reqId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VApp_stuff;
