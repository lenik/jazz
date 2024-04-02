import { STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import UserRun from "./UserRun";
import UserRunValidators from "./UserRunValidators";
import _UserRun_stuff_TypeInfo from "./_UserRun_stuff_TypeInfo";

export class UserRunTypeInfo extends _UserRun_stuff_TypeInfo {

    readonly validators = new UserRunValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserRun"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Activity Log"; }

    override create() {
        return new UserRun();
    }

    override preamble() {
        super.preamble();
        this.declare({
            activeTime: property({ type: OffsetDateTime.TYPE, validator: this.validators.validateActiveTime }),
            stateText: property({ type: STRING, validator: this.validators.validateStateText }),
        });
    }

    static readonly INSTANCE = new UserRunTypeInfo();

}

export default UserRunTypeInfo;

export const UserRun_TYPE = UserRunTypeInfo.INSTANCE;
