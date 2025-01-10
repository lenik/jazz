import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type UserBadgeTypeInfo from "./UserBadgeTypeInfo";
import _UserBadge_stuff_Validators from "./_UserBadge_stuff_Validators";

export class UserBadgeValidators extends _UserBadge_stuff_Validators {

    constructor(type: UserBadgeTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserBadgeTypeInfo;
    }

}

export default UserBadgeValidators;
