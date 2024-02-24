import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PartyCategoryTypeInfo from "./PartyCategoryTypeInfo";
import _PartyCategory_stuff_Validators from "./_PartyCategory_stuff_Validators";

export class PartyCategoryValidators extends _PartyCategory_stuff_Validators {

    constructor(type: PartyCategoryTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PartyCategoryTypeInfo;
    }

}

export default PartyCategoryValidators;
