import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserOtherIdTypeValidators from "./UserOtherIdTypeValidators";
import _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

// Type Info

export class UserOtherIdTypeTypeInfo extends _UserOtherIdType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    static validators = new UserOtherIdTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserOtherIdTypeTypeInfo.declaredProperty);
    }

}

export default UserOtherIdType;
