import type { List, long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryParty from "./DiaryParty";
import type DiaryTypeInfo from "./DiaryTypeInfo";
import _Diary_stuff_Validators from "./_Diary_stuff_Validators";

export class DiaryValidators extends _Diary_stuff_Validators {

    constructor(type: DiaryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryTypeInfo;
    }

    validateParties(val: List<DiaryParty>) {
    }

}

export default DiaryValidators;
