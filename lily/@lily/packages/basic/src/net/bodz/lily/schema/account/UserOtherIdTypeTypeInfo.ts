import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserOtherIdTypeValidators from "./UserOtherIdTypeValidators";
import _UserOtherIdType_stuff_TypeInfo from "./_UserOtherIdType_stuff_TypeInfo";

export class UserOtherIdTypeTypeInfo extends _UserOtherIdType_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    validators = new UserOtherIdTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserOtherIdTypeTypeInfo;
