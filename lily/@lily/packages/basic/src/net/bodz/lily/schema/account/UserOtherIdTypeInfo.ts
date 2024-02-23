import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserOtherIdValidators from "./UserOtherIdValidators";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

// Type Info

export class UserOtherIdTypeInfo extends _UserOtherId_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserOtherId"
    icon = "fa-tag"
    description = "User Open ID"

    static validators = new UserOtherIdValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserOtherIdTypeInfo.declaredProperty);
    }

}

export default UserOtherId;
