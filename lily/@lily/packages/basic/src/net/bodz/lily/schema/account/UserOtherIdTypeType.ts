import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserOtherIdTypeValidators from "./UserOtherIdTypeValidators";
import _UserOtherIdType_stuff_Type from "./_UserOtherIdType_stuff_Type";

// Type Info

export class UserOtherIdTypeType extends _UserOtherIdType_stuff_Type {

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    static validators = new UserOtherIdTypeValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserOtherIdTypeType.declaredProperty);
    }

}

export default UserOtherIdType;
