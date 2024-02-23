import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import { Set } from "../../../../../java/util/Set";
import { UserRun } from "./UserRun";
import { UserSecret } from "./UserSecret";
import UserValidators from "./UserValidators";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

// Type Info

export class UserTypeInfo extends _User_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.User"
    icon = "fa-tag"
    label = "User (Account)"
    description = "User Account"

    static validators = new UserValidators();

    static declaredProperty: EntityPropertyMap = {
        groupIds: property({ type: "Set", validator: this.validators.validateGroupIds }),
        groups: property({ type: "List", validator: this.validators.validateGroups }),
        ids: property({ type: "List", validator: this.validators.validateIds }),
        otherIds: property({ type: "List", validator: this.validators.validateOtherIds }),
        registerIP: property({ type: "string", label: "Register IP", validator: this.validators.validateRegisterIP }),
        runningState: property({ type: "UserRun", validator: this.validators.validateRunningState }),
        secret: property({ type: "UserSecret", validator: this.validators.validateSecret }),
        secrets: property({ type: "List", validator: this.validators.validateSecrets }),
        superUser: property({ type: "boolean", nullable: false, validator: this.validators.validateSuperUser }),
    }

    constructor() {
        super();
        this.declare(UserTypeInfo.declaredProperty);
    }

}

export default User;
