import type { int, long } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ApiType from "./ApiType";
import type VAppRequest from "./VAppRequest";
import _VAppRequestApi_stuff_TypeInfo from "./_VAppRequestApi_stuff_TypeInfo";

export class _VAppRequestApi_stuff extends CoEntity<long> {

    static _typeInfo: _VAppRequestApi_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VAppRequestApi_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;

    parent: VAppRequest;
    parentId: int;

    api: ApiType;
    apiId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VAppRequestApi_stuff;
