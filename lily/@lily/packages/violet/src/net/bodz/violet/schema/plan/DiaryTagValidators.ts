import { ValidateResult } from "skel01-core/src/ui/types";

import type DiaryTagTypeInfo from "./DiaryTagTypeInfo";
import _DiaryTag_stuff_Validators from "./_DiaryTag_stuff_Validators";

export class DiaryTagValidators extends _DiaryTag_stuff_Validators {

    constructor(type: DiaryTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryTagTypeInfo;
    }

}

export default DiaryTagValidators;
