import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class UserTypeTypeInfo extends _UserType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserType"
    icon = "fa-tag"
    description = "User Type"

    validators = new UserTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserTypeTypeInfo;
