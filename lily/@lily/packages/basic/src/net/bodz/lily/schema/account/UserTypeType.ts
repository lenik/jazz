import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_Type from "./_UserType_stuff_Type";

// Type Info

export class UserTypeType extends _UserType_stuff_Type {

    name = "net.bodz.lily.schema.account.UserType"
    icon = "fa-tag"
    description = "User Type"

    static validators = new UserTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserTypeType.declaredProperty);
    }

}

export default UserType;
