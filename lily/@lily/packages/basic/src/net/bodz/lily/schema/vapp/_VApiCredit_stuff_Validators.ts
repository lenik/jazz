import type { BigDecimal, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import IdEntityValidators from "../../concrete/IdEntityValidators";
import type ApiType from "./ApiType";
import type VApp from "./VApp";
import type _VApiCredit_stuff_TypeInfo from "./_VApiCredit_stuff_TypeInfo";

export class _VApiCredit_stuff_Validators extends IdEntityValidators {

    constructor(type: _VApiCredit_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _VApiCredit_stuff_TypeInfo;
    }

    validateCredit(val: BigDecimal) {
    }

    validateApi(val: ApiType) {
    }

    validateApp(val: VApp) {
    }

}

export default _VApiCredit_stuff_Validators;
