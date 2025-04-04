import type { long } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
