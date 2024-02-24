import type { integer } from "@skeljs/core/src/lang/type";
import { ValidateResult } from "@skeljs/core/src/ui/types";

import type Group from "./Group";
import type UserOtherId from "./UserOtherId";
import type UserRun from "./UserRun";
import type UserSecret from "./UserSecret";
import type UserTypeInfo from "./UserTypeInfo";
import _User_stuff_Validators from "./_User_stuff_Validators";

export class UserValidators extends _User_stuff_Validators {

    constructor(type: UserTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserTypeInfo;
    }

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
