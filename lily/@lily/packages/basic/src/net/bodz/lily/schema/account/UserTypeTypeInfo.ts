import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

// Type Info

export class UserTypeTypeInfo extends _UserType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserType"
    icon = "fa-tag"
    description = "User Type"

    static validators = new UserTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserTypeTypeInfo.declaredProperty);
    }

}

export default UserType;
