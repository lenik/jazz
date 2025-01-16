import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import { Person_TYPE } from "../contact/PersonTypeInfo";
import { Group_TYPE } from "./GroupTypeInfo";
import { User_TYPE } from "./UserTypeInfo";
import { UserType_TYPE } from "./UserTypeTypeInfo";
import _User_stuff_Validators from "./_User_stuff_Validators";

export class _User_stuff_TypeInfo extends CoPrincipalTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user";

    static readonly FIELD_TYPE_ID = "type";
    static readonly FIELD_PRIMARY_GROUP_ID = "gid0";
    static readonly FIELD_REFERER_ID = "referer";
    static readonly FIELD_PERSON_ID = "person";

    static readonly N_TYPE_ID = 10;
    static readonly N_PRIMARY_GROUP_ID = 10;
    static readonly N_REFERER_ID = 10;
    static readonly N_PERSON_ID = 10;

    readonly validators = new _User_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.account.User"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Account"; }

    override preamble() {
        super.preamble();
        this.declare({

            person: property({ type: Person_TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            primaryGroup: property({ type: Group_TYPE, nullable: false, label: "Primary Group", 
                description: "The primary user group, the default value of ownerGroup.", 
                validator: this.validators.validatePrimaryGroup }),
            primaryGroupId: property({ type: INT, nullable: false, precision: 10, label: "Primary Group", 
                description: "The primary user group, the default value of ownerGroup." }),

            referer: property({ type: this, label: "Referer", 
                description: "The referer user (used for promotion)", 
                validator: this.validators.validateReferer }),
            refererId: property({ type: INT, precision: 10, label: "Referer", 
                description: "The referer user (used for promotion)" }),

            type: property({ type: UserType_TYPE, nullable: false, label: "Type", 
                description: "User type like system-user, normal-user, etc.", 
                validator: this.validators.validateType }),
            typeId: property({ type: INT, nullable: false, precision: 10, label: "Type", 
                description: "User type like system-user, normal-user, etc." }),
        });
    }

    static readonly INSTANCE = new _User_stuff_TypeInfo();

}

export default _User_stuff_TypeInfo;

export const _User_stuff_TYPE = _User_stuff_TypeInfo.INSTANCE;
