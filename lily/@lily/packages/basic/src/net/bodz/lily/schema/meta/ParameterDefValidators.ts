import { ValidateResult } from "skel01-core/src/ui/types";

import type ParameterDefTypeInfo from "./ParameterDefTypeInfo";
import _ParameterDef_stuff_Validators from "./_ParameterDef_stuff_Validators";

export class ParameterDefValidators extends _ParameterDef_stuff_Validators {

    constructor(type: ParameterDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as ParameterDefTypeInfo;
    }

}

export default ParameterDefValidators;
