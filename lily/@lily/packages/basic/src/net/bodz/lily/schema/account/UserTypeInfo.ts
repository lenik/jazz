import type { InetAddress, int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import List from "../../../../../java/util/List";
import Group from "./Group";
import UserOtherId from "./UserOtherId";
import UserRun from "./UserRun";
import UserSecret from "./UserSecret";
import UserValidators from "./UserValidators";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class UserTypeInfo extends _User_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.account.User"; }
    get icon() { return "fa-tag"; }
    get label() { return "User (Account)"; }
    get description() { return "User Account"; }

    validators = new UserValidators(this);

    declaredProperty: EntityPropertyMap = {
        groupIds: property({ type: "Set<int>", validator: this.validators.validateGroupIds }),
        groups: property({ type: "List<Group>", validator: this.validators.validateGroups }),
        ids: property({ type: "List<UserOtherId>", validator: this.validators.validateIds }),
        otherIds: property({ type: "List<UserOtherId>", validator: this.validators.validateOtherIds }),
        registerIP: property({ type: "InetAddress", label: "Register IP", validator: this.validators.validateRegisterIP }),
        runningState: property({ type: "UserRun", validator: this.validators.validateRunningState }),
        secret: property({ type: "UserSecret", validator: this.validators.validateSecret }),
        secrets: property({ type: "List<UserSecret>", validator: this.validators.validateSecrets }),
        superUser: property({ type: "boolean", nullable: false, validator: this.validators.validateSuperUser }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default UserTypeInfo;
