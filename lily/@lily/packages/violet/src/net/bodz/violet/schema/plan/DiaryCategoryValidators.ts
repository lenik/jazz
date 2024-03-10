import type { int } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryCategoryTypeInfo from "./DiaryCategoryTypeInfo";
import _DiaryCategory_stuff_Validators from "./_DiaryCategory_stuff_Validators";

export class DiaryCategoryValidators extends _DiaryCategory_stuff_Validators {

    constructor(type: DiaryCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryCategoryTypeInfo;
    }

}

export default DiaryCategoryValidators;
