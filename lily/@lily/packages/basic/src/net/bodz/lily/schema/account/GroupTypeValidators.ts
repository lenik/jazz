import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

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
