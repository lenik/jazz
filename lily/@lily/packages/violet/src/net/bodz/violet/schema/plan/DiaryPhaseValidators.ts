import { ValidateResult } from "skel01-core/src/ui/types";

import type DiaryPhaseTypeInfo from "./DiaryPhaseTypeInfo";
import _DiaryPhase_stuff_Validators from "./_DiaryPhase_stuff_Validators";

export class DiaryPhaseValidators extends _DiaryPhase_stuff_Validators {

    constructor(type: DiaryPhaseTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryPhaseTypeInfo;
    }

}

export default DiaryPhaseValidators;
