import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int, long } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class _VApi_stuff extends CoEntity<long> {

    static _typeInfo: _VApi_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VApi_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: long;
    properties?: JsonVariant;
    callback?: string;

    api: ApiType;
    apiId: int;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VApi_stuff;
