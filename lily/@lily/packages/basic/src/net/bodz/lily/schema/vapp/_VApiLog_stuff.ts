import type { int, long } from "skel01-core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApiLog_stuff_TypeInfo from "./_VApiLog_stuff_TypeInfo";

export class _VApiLog_stuff extends IdEntity<long> {

    static _typeInfo: _VApiLog_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VApiLog_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    message?: string;
    err?: string;

    api?: ApiType;
    apiId?: int;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VApiLog_stuff;
