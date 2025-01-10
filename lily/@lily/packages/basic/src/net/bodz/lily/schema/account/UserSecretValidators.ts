import type { int } from "skel01-core/src/lang/basetype";
import { ValidateResult } from "skel01-core/src/ui/types";

import type UserSecretTypeInfo from "./UserSecretTypeInfo";
import _UserSecret_stuff_Validators from "./_UserSecret_stuff_Validators";

export class UserSecretValidators extends _UserSecret_stuff_Validators {

    constructor(type: UserSecretTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as UserSecretTypeInfo;
    }

}

export default UserSecretValidators;
