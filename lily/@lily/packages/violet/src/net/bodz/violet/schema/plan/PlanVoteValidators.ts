import { ValidateResult } from "skel01-core/src/ui/types";

import type PlanVoteTypeInfo from "./PlanVoteTypeInfo";
import _PlanVote_stuff_Validators from "./_PlanVote_stuff_Validators";

export class PlanVoteValidators extends _PlanVote_stuff_Validators {

    constructor(type: PlanVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PlanVoteTypeInfo;
    }

}

export default PlanVoteValidators;
