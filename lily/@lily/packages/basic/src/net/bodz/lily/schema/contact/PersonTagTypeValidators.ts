import { ValidateResult } from "@skeljs/core/src/ui/types";

import type PersonTagTypeTypeInfo from "./PersonTagTypeTypeInfo";
import _PersonTagType_stuff_Validators from "./_PersonTagType_stuff_Validators";

export class PersonTagTypeValidators extends _PersonTagType_stuff_Validators {

    constructor(type: PersonTagTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as PersonTagTypeTypeInfo;
    }

}

export default PersonTagTypeValidators;
