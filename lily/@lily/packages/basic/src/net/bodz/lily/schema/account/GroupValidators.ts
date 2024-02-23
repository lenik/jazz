import { ValidateResult } from "@skeljs/core/src/ui/types";

import { Group } from "./Group";
import { User } from "./User";
import _Group_stuffValidators from "./_Group_stuffValidators";

export class GroupValidators extends _Group_stuffValidators {
    validateChildren(val: Group[]) {
    }
    validateUsers(val: User[]) {
    }

}

export default GroupValidators;
