import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserSecretValidators from "./UserSecretValidators";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class UserSecretTypeInfo extends _UserSecret_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserSecret"
    icon = "fa-tag"
    description = "User Secret"

    validators = new UserSecretValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserSecretTypeInfo;
