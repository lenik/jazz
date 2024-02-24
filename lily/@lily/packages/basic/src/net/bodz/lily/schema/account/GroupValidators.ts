import { ValidateResult } from "@skeljs/core/src/ui/types";

import type Group from "./Group";
import type GroupTypeInfo from "./GroupTypeInfo";
import type User from "./User";
import _Group_stuff_Validators from "./_Group_stuff_Validators";

export class GroupValidators extends _Group_stuff_Validators {

    constructor(type: GroupTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as GroupTypeInfo;
    }

    validateChildren(val: Group[]) {
    }

    validateUsers(val: User[]) {
    }

}

export default GroupValidators;
