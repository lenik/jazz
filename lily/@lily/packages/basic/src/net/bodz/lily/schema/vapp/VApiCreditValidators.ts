import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type VApiCreditTypeInfo from "./VApiCreditTypeInfo";
import _VApiCredit_stuff_Validators from "./_VApiCredit_stuff_Validators";

export class VApiCreditValidators extends _VApiCredit_stuff_Validators {

    constructor(type: VApiCreditTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VApiCreditTypeInfo;
    }

}

export default VApiCreditValidators;
