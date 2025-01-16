import type { InetAddress, List, int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type ExtraAttributes from "../../concrete/util/ExtraAttributes";
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

    validateGroupIds(val: Set<int>) {
    }

    validateGroups(val: List<Group>) {
    }

    validateIds(val: List<UserOtherId>) {
    }

    validateOtherIds(val: List<UserOtherId>) {
    }

    validateRegisterIP(val: InetAddress) {
    }

    validateRunningState(val: UserRun) {
    }

    validateSecret(val: UserSecret) {
    }

    validateSecrets(val: List<UserSecret>) {
    }

    validateSuperUser(val: boolean) {
    }

}

export default UserValidators;
