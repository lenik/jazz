import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryReviewTypeInfo from "./DiaryReviewTypeInfo";
import _DiaryReview_stuff_Validators from "./_DiaryReview_stuff_Validators";

export class DiaryReviewValidators extends _DiaryReview_stuff_Validators {

    constructor(type: DiaryReviewTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryReviewTypeInfo;
    }

}

export default DiaryReviewValidators;
