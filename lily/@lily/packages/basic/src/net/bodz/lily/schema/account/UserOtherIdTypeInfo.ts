import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import UserOtherIdValidators from "./UserOtherIdValidators";
import _UserOtherId_stuff_TypeInfo from "./_UserOtherId_stuff_TypeInfo";

export class UserOtherIdTypeInfo extends _UserOtherId_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserOtherId"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Open ID"; }

    validators = new UserOtherIdValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserOtherIdTypeInfo;
