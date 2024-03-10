import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoPrincipalTypeInfo from "../../concrete/CoPrincipalTypeInfo";
import Person from "../contact/Person";
import Group from "./Group";
import User from "./User";
import UserType from "./UserType";
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
    get label() { return "User (Account)"; }
    get description() { return "User Account"; }

    override preamble() {
        super.preamble();
        this.declare({

            person: property({ type: Person.TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            primaryGroup: property({ type: Group.TYPE, nullable: false, 
                description: "The primary user group, the default value of ownerGroup.", 
                validator: this.validators.validatePrimaryGroup }),
            primaryGroupId: property({ type: INT, nullable: false, precision: 10, 
                description: "The primary user group, the default value of ownerGroup." }),

            referer: property({ type: this, 
                description: "The referer user (used for promotion)", 
                validator: this.validators.validateReferer }),
            refererId: property({ type: INT, precision: 10, 
                description: "The referer user (used for promotion)" }),

            type: property({ type: UserType.TYPE, nullable: false, 
                description: "User type like system-user, normal-user, etc.", 
                validator: this.validators.validateType }),
            typeId: property({ type: INT, nullable: false, precision: 10, 
                description: "User type like system-user, normal-user, etc." }),
        });
    }

    static readonly INSTANCE = new _User_stuff_TypeInfo();

}

export default _User_stuff_TypeInfo;
