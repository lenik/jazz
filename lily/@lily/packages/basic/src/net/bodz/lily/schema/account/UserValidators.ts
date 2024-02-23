import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import { Group } from "./Group";
import { UserOtherId } from "./UserOtherId";
import { UserRun } from "./UserRun";
import { UserSecret } from "./UserSecret";
import _User_stuffValidators from "./_User_stuffValidators";

export class UserValidators extends _User_stuffValidators {
    validateGroupIds(val: integer[]) {
    }
    validateGroups(val: Group[]) {
    }
    validateIds(val: UserOtherId[]) {
    }
    validateOtherIds(val: UserOtherId[]) {
    }
    validateRegisterIP(val: string) {
    }
    validateRunningState(val: UserRun) {
    }
    validateSecret(val: UserSecret) {
    }
    validateSecrets(val: UserSecret[]) {
    }
    validateSuperUser(val: boolean) {
    }

}

export default UserValidators;
