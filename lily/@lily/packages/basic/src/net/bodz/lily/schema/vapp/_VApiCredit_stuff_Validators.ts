import type { BigDecimal, int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import CoEntityValidators from "../../concrete/CoEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class _VApiCredit_stuff_Validators extends CoEntityValidators {

    constructor(type: _VApiCredit_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApiCredit_stuff_TypeInfo;
    }

    validateId(val: int) {
    }

    validateCredit(val: BigDecimal) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApiCredit_stuff_Validators;
