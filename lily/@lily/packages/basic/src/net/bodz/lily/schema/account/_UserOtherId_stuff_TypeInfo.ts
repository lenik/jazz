import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import User from "./User";
import UserOtherIdType from "./UserOtherIdType";
import _UserOtherId_stuff_Validators from "./_UserOtherId_stuff_Validators";

export class _UserOtherId_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "useroid";

    static readonly FIELD_ID = "id";
    static readonly FIELD_BEGIN_TIME = "t0";
    static readonly FIELD_END_TIME = "t1";
    static readonly FIELD_YEAR = "year";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_USER_ID = "user";
    static readonly FIELD_TYPE_ID = "type";
    static readonly FIELD_OTHER_ID = "oid";
    static readonly FIELD_AUTH = "auth";

    static readonly N_ID = 10;
    static readonly N_BEGIN_TIME = 35;
    static readonly N_END_TIME = 35;
    static readonly N_YEAR = 10;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_USER_ID = 10;
    static readonly N_TYPE_ID = 10;
    static readonly N_OTHER_ID = 100;
    static readonly N_AUTH = 2147483647;

    readonly validators = new _UserOtherId_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.UserOtherId"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Open ID"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            beginTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
            endTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateEndTime }),
            year: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateYear }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            otherId: property({ type: STRING, nullable: false, precision: 100, 
                description: "The identity data", 
                validator: this.validators.validateOtherId }),
            auth: property({ type: JSON_VARIANT, 
                description: "The authentication data", 
                validator: this.validators.validateAuth }),

            type: property({ type: UserOtherIdType.TYPE, nullable: false, 
                description: "Type of Open ID", 
                validator: this.validators.validateType }),
            typeId: property({ type: INT, nullable: false, precision: 10, 
                description: "Type of Open ID" }),

            user: property({ type: User.TYPE, nullable: false, 
                description: "The declaring user", 
                validator: this.validators.validateUser }),
            userId: property({ type: INT, nullable: false, precision: 10, 
                description: "The declaring user" }),
        });
    }

    static readonly INSTANCE = new _UserOtherId_stuff_TypeInfo();

}

export default _UserOtherId_stuff_TypeInfo;
