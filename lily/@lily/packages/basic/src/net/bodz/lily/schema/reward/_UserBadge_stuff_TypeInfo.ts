import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import type User from "../account/User";
import Badge from "./Badge";
import _UserBadge_stuff_Validators from "./_UserBadge_stuff_Validators";

export class _UserBadge_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_badge";

    get name() { return "net.bodz.lily.schema.reward.UserBadge"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_USER_ID = "user";
    static FIELD_BADGE_ID = "badge";

    static N_ID = 10;
    static N_USER_ID = 10;
    static N_BADGE_ID = 10;

    validators = new _UserBadge_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

            badge: property({ type: Badge.TYPE, validator: this.validators.validateBadge }),
            badgeId: property({ type: INT, precision: 10 }),

            user: property({ type: User.TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _UserBadge_stuff_TypeInfo;
