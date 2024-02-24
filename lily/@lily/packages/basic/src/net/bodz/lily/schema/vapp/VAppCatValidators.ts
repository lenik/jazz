import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type VAppCatTypeInfo from "./VAppCatTypeInfo";
import _VAppCat_stuff_Validators from "./_VAppCat_stuff_Validators";

export class VAppCatValidators extends _VAppCat_stuff_Validators {

    constructor(type: VAppCatTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as VAppCatTypeInfo;
    }

}

export default VAppCatValidators;
