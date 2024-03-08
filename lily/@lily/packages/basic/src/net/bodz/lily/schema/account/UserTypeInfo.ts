import { BOOLEAN, INET_ADDRESS, INT, LIST, SET } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

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

    override preamble() {
        super.preamble();
        this.declare({
            groupIds: property({ type: SET(INT), validator: this.validators.validateGroupIds }),
            groups: property({ type: LIST(Group.TYPE), validator: this.validators.validateGroups }),
            ids: property({ type: LIST(UserOtherId.TYPE), validator: this.validators.validateIds }),
            otherIds: property({ type: LIST(UserOtherId.TYPE), validator: this.validators.validateOtherIds }),
            registerIP: property({ type: INET_ADDRESS, label: "Register IP", validator: this.validators.validateRegisterIP }),
            runningState: property({ type: UserRun.TYPE, validator: this.validators.validateRunningState }),
            secret: property({ type: UserSecret.TYPE, validator: this.validators.validateSecret }),
            secrets: property({ type: LIST(UserSecret.TYPE), validator: this.validators.validateSecrets }),
            superUser: property({ type: BOOLEAN, nullable: false, validator: this.validators.validateSuperUser }),
        });
    }

    constructor() {
        super();
    }

}

export default UserTypeInfo;
