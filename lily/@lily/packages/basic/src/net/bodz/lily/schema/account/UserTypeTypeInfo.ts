import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import UserTypeValidators from "./UserTypeValidators";
import _UserType_stuff_TypeInfo from "./_UserType_stuff_TypeInfo";

export class UserTypeTypeInfo extends _UserType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.UserType"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Type"; }

    validators = new UserTypeValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserTypeTypeInfo;
