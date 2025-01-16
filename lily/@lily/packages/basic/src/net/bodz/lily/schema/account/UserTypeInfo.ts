import { BOOLEAN, INET_ADDRESS, INT, LIST, SET, STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { ExtraAttributes_TYPE } from "../../concrete/util/ExtraAttributesTypeInfo";
import { Group_TYPE } from "./GroupTypeInfo";
import User from "./User";
import { UserOtherId_TYPE } from "./UserOtherIdTypeInfo";
import { UserRun_TYPE } from "./UserRunTypeInfo";
import { UserSecret_TYPE } from "./UserSecretTypeInfo";
import UserValidators from "./UserValidators";
import _User_stuff_TypeInfo from "./_User_stuff_TypeInfo";

export class UserTypeInfo extends _User_stuff_TypeInfo {

    readonly validators = new UserValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.User"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Account"; }

    override create() {
        return new User();
    }

    override preamble() {
        super.preamble();
        this.declare({
            groupIds: property({ type: SET(INT), validator: this.validators.validateGroupIds }),
            groups: property({ type: LIST(Group_TYPE), validator: this.validators.validateGroups }),
            ids: property({ type: LIST(UserOtherId_TYPE), validator: this.validators.validateIds }),
            otherIds: property({ type: LIST(UserOtherId_TYPE), validator: this.validators.validateOtherIds }),
            registerIP: property({ type: INET_ADDRESS, 
                description: "", 
                validator: this.validators.validateRegisterIP }),
            runningState: property({ type: UserRun_TYPE, validator: this.validators.validateRunningState }),
            secret: property({ type: UserSecret_TYPE, validator: this.validators.validateSecret }),
            secrets: property({ type: LIST(UserSecret_TYPE), validator: this.validators.validateSecrets }),
            superUser: property({ type: BOOLEAN, nullable: false, validator: this.validators.validateSuperUser }),
        });
    }

    static readonly INSTANCE = new UserTypeInfo();

}

export default UserTypeInfo;

export const User_TYPE = UserTypeInfo.INSTANCE;
