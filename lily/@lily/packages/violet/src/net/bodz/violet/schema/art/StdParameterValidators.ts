import { ValidateResult } from "skel01-core/src/ui/types";

import type StdParameterTypeInfo from "./StdParameterTypeInfo";
import _StdParameter_stuff_Validators from "./_StdParameter_stuff_Validators";

export class StdParameterValidators extends _StdParameter_stuff_Validators {

    constructor(type: StdParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as StdParameterTypeInfo;
    }

}

export default StdParameterValidators;
