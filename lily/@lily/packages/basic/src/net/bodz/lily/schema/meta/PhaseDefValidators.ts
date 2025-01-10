import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type PhaseDefTypeInfo from "./PhaseDefTypeInfo";
import _PhaseDef_stuff_Validators from "./_PhaseDef_stuff_Validators";

export class PhaseDefValidators extends _PhaseDef_stuff_Validators {

    constructor(type: PhaseDefTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PhaseDefTypeInfo;
    }

}

export default PhaseDefValidators;
