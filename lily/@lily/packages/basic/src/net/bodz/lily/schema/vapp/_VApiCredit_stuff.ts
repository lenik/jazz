import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class _VApiCredit_stuff extends CoEntity<int> {
    static _typeInfo: _VApiCredit_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _VApiCredit_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
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
