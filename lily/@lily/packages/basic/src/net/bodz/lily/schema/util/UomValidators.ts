import { ValidateResult } from "skel01-core/src/ui/types";

import type UomTypeInfo from "./UomTypeInfo";
import _Uom_stuff_Validators from "./_Uom_stuff_Validators";

export class UomValidators extends _Uom_stuff_Validators {

    constructor(type: UomTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UomTypeInfo;
    }

}

export default UomValidators;
