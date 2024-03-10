import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryParameterTypeInfo from "./DiaryParameterTypeInfo";
import _DiaryParameter_stuff_Validators from "./_DiaryParameter_stuff_Validators";

export class DiaryParameterValidators extends _DiaryParameter_stuff_Validators {

    constructor(type: DiaryParameterTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryParameterTypeInfo;
    }

}

export default DiaryParameterValidators;
