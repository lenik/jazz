import { ValidateResult } from "skel01-core/src/ui/types";

import type DiaryParameterTypeTypeInfo from "./DiaryParameterTypeTypeInfo";
import _DiaryParameterType_stuff_Validators from "./_DiaryParameterType_stuff_Validators";

export class DiaryParameterTypeValidators extends _DiaryParameterType_stuff_Validators {

    constructor(type: DiaryParameterTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryParameterTypeTypeInfo;
    }

}

export default DiaryParameterTypeValidators;
