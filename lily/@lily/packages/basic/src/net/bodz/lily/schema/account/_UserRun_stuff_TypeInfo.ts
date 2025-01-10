import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INET_ADDRESS, INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { primaryKey, property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import { User_TYPE } from "./UserTypeInfo";
import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class _UserRun_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_run";

    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_SCORE = "score";
    static readonly FIELD_LAST_LOGIN_TIME = "lastlog";
    static readonly FIELD_LAST_LOGIN_I_P = "lastlogip";

    static readonly N_USER_ID = 10;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_SCORE = 10;
    static readonly N_LAST_LOGIN_TIME = 35;
    static readonly N_LAST_LOGIN_I_P = 2147483647;

    readonly validators = new _UserRun_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserRun"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Activity Log"; }

    override preamble() {
        super.preamble();
        this.declare({
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            score: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateScore }),
            lastLoginTime: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, 
                description: "Last time of login", 
                validator: this.validators.validateLastLoginTime }),
            lastLoginIP: property({ type: INET_ADDRESS, 
                description: "The source IP of last login", 
                validator: this.validators.validateLastLoginIP }),

            user: property({ type: User_TYPE, nullable: false, 
                description: "The user", 
                validator: this.validators.validateUser }),
            userId: primaryKey({ type: INT, nullable: false, precision: 10, 
                description: "The user" }),
        });
    }

    static readonly INSTANCE = new _UserRun_stuff_TypeInfo();

}

export default _UserRun_stuff_TypeInfo;

export const _UserRun_stuff_TYPE = _UserRun_stuff_TypeInfo.INSTANCE;
