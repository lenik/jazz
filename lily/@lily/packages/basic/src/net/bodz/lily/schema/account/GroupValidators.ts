import { ValidateResult } from "@skeljs/core/src/ui/types";

import { Group } from "./Group";
import { User } from "./User";
import _Group_stuff_Validators from "./_Group_stuff_Validators";

export class GroupValidators extends _Group_stuff_Validators {
    validateChildren(val: Group[]) {
    }
    validateUsers(val: User[]) {
    }

}

export default GroupValidators;
