import type { long } from "@skeljs/core/src/lang/basetype";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type DiaryPartyTypeInfo from "./DiaryPartyTypeInfo";
import _DiaryParty_stuff_Validators from "./_DiaryParty_stuff_Validators";

export class DiaryPartyValidators extends _DiaryParty_stuff_Validators {

    constructor(type: DiaryPartyTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as DiaryPartyTypeInfo;
    }

}

export default DiaryPartyValidators;
