import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanDoVoteTypeInfo from "./PlanDoVoteTypeInfo";
import _PlanDoVote_stuff_Validators from "./_PlanDoVote_stuff_Validators";

export class PlanDoVoteValidators extends _PlanDoVote_stuff_Validators {

    constructor(type: PlanDoVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanDoVoteTypeInfo;
    }

}

export default PlanDoVoteValidators;
