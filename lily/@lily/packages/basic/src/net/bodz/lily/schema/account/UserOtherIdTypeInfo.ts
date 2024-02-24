import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserOtherIdValidators from "./UserOtherIdValidators";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class UserOtherIdTypeInfo extends _UserOtherId_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserOtherId"
    icon = "fa-tag"
    description = "User Open ID"

    validators = new UserOtherIdValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserOtherIdTypeInfo;
