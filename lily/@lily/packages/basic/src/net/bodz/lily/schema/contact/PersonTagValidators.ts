import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PersonTagTypeInfo from "./PersonTagTypeInfo";
import _PersonTag_stuff_Validators from "./_PersonTag_stuff_Validators";

export class PersonTagValidators extends _PersonTag_stuff_Validators {

    constructor(type: PersonTagTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PersonTagTypeInfo;
    }

}

export default PersonTagValidators;
