import { ValidateResult } from "skel01-core/src/ui/types";

import type ParameterValueTypeInfo from "./ParameterValueTypeInfo";
import _ParameterValue_stuff_Validators from "./_ParameterValue_stuff_Validators";

export class ParameterValueValidators extends _ParameterValue_stuff_Validators {

    constructor(type: ParameterValueTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ParameterValueTypeInfo;
    }

}

export default ParameterValueValidators;
