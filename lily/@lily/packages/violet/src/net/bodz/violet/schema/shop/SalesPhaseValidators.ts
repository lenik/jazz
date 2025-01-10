import { ValidateResult } from "skel01-core/src/ui/types";

import type SalesPhaseTypeInfo from "./SalesPhaseTypeInfo";
import _SalesPhase_stuff_Validators from "./_SalesPhase_stuff_Validators";

export class SalesPhaseValidators extends _SalesPhase_stuff_Validators {

    constructor(type: SalesPhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as SalesPhaseTypeInfo;
    }

}

export default SalesPhaseValidators;
