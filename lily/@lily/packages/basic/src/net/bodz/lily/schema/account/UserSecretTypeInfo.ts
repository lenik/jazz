import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import UserSecretValidators from "./UserSecretValidators";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

// Type Info

export class UserSecretTypeInfo extends _UserSecret_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.UserSecret"
    icon = "fa-tag"
    description = "User Secret"

    static validators = new UserSecretValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(UserSecretTypeInfo.declaredProperty);
    }

}

export default UserSecret;
