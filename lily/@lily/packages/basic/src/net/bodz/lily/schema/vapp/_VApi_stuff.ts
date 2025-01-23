import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int, long } from "skel01-core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApi_stuff_TypeInfo from "./_VApi_stuff_TypeInfo";

export class _VApi_stuff extends IdEntity<long> {

    static _typeInfo: _VApi_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VApi_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    properties?: JsonVariant;
    files?: JsonVariant;
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
