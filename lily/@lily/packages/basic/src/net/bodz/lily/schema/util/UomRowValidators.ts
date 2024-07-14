import { ValidateResult } from "@skeljs/core/src/ui/types";

import type UomRowTypeInfo from "./UomRowTypeInfo";
import _UomRow_stuff_Validators from "./_UomRow_stuff_Validators";

export class UomRowValidators extends _UomRow_stuff_Validators {

    constructor(type: UomRowTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UomRowTypeInfo;
    }

}

export default UomRowValidators;
