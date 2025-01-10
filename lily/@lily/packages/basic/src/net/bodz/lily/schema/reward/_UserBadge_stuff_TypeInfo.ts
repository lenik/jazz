import { INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { User_TYPE } from "../account/UserTypeInfo";
import { Badge_TYPE } from "./BadgeTypeInfo";
import _UserBadge_stuff_Validators from "./_UserBadge_stuff_Validators";

export class _UserBadge_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_badge";

    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_BADGE_ID = "badge";

    static readonly N_USER_ID = 10;
    static readonly N_BADGE_ID = 10;

    readonly validators = new _UserBadge_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.reward.UserBadge"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            badge: property({ type: Badge_TYPE, validator: this.validators.validateBadge }),
            badgeId: property({ type: INT, precision: 10 }),

            user: property({ type: User_TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _UserBadge_stuff_TypeInfo();

}

export default _UserBadge_stuff_TypeInfo;

export const _UserBadge_stuff_TYPE = _UserBadge_stuff_TypeInfo.INSTANCE;
