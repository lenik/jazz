import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";
import CoMessageValidators from "@lily/basic/src/net/bodz/lily/concrete/CoMessageValidators";

import type Diary from "./Diary";
import type DiaryReview from "./DiaryReview";
import type _DiaryReview_stuff_TypeInfo from "./_DiaryReview_stuff_TypeInfo";

export class _DiaryReview_stuff_Validators extends CoMessageValidators {

    constructor(type: _DiaryReview_stuff_TypeInfo) {
        super(type);
    }

    get type() {
        return this._type as _DiaryReview_stuff_TypeInfo;
    }

    validateFormArguments(val: string) {
    }

    validateDiary(val: Diary) {
    }

    validateParent(val: DiaryReview) {
    }

}

export default _DiaryReview_stuff_Validators;
