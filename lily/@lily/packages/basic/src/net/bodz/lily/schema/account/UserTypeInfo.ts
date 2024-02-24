import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import Group from "./Group";
import UserOtherId from "./UserOtherId";
import UserRun from "./UserRun";
import UserSecret from "./UserSecret";
import UserValidators from "./UserValidators";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class UserTypeInfo extends _User_stuff_TypeInfo {

    name = "net.bodz.lily.schema.account.User"
    icon = "fa-tag"
    label = "User (Account)"
    description = "User Account"

    validators = new UserValidators(this);

    declaredProperty: EntityPropertyMap = {
        groupIds: property({ type: "integer[]", validator: this.validators.validateGroupIds }),
        groups: property({ type: "Group[]", validator: this.validators.validateGroups }),
        ids: property({ type: "UserOtherId[]", validator: this.validators.validateIds }),
        otherIds: property({ type: "UserOtherId[]", validator: this.validators.validateOtherIds }),
        registerIP: property({ type: "string", label: "Register IP", validator: this.validators.validateRegisterIP }),
        runningState: property({ type: "UserRun", validator: this.validators.validateRunningState }),
        secret: property({ type: "UserSecret", validator: this.validators.validateSecret }),
        secrets: property({ type: "UserSecret[]", validator: this.validators.validateSecrets }),
        superUser: property({ type: "boolean", nullable: false, validator: this.validators.validateSuperUser }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserTypeInfo;
