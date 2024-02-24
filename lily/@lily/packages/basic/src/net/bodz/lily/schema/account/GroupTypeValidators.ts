import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type GroupTypeTypeInfo from "./GroupTypeTypeInfo";
import _GroupType_stuff_Validators from "./_GroupType_stuff_Validators";

export class GroupTypeValidators extends _GroupType_stuff_Validators {

    constructor(type: GroupTypeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as GroupTypeTypeInfo;
    }

}

export default GroupTypeValidators;
