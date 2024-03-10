import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryVoteTypeInfo from "./DiaryVoteTypeInfo";
import _DiaryVote_stuff_Validators from "./_DiaryVote_stuff_Validators";

export class DiaryVoteValidators extends _DiaryVote_stuff_Validators {

    constructor(type: DiaryVoteTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryVoteTypeInfo;
    }

}

export default DiaryVoteValidators;
