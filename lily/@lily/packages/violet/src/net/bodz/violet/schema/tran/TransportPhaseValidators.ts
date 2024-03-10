import { ValidateResult } from "@skeljs/core/src/ui/types";

import type TransportPhaseTypeInfo from "./TransportPhaseTypeInfo";
import _TransportPhase_stuff_Validators from "./_TransportPhase_stuff_Validators";

export class TransportPhaseValidators extends _TransportPhase_stuff_Validators {

    constructor(type: TransportPhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as TransportPhaseTypeInfo;
    }

}

export default TransportPhaseValidators;
