import { ValidateResult } from "skel01-core/src/ui/types";

import type IssuePhaseTypeInfo from "./IssuePhaseTypeInfo";
import _IssuePhase_stuff_Validators from "./_IssuePhase_stuff_Validators";

export class IssuePhaseValidators extends _IssuePhase_stuff_Validators {

    constructor(type: IssuePhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as IssuePhaseTypeInfo;
    }

}

export default IssuePhaseValidators;
