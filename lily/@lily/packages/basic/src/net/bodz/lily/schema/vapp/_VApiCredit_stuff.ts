import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class _VApiCredit_stuff extends IdEntity<int> {

    static _typeInfo: _VApiCredit_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _VApiCredit_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    credit: BigDecimal;

    api: ApiType;
    apiId: int;

    app: VApp;
    appId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _VApiCredit_stuff;
