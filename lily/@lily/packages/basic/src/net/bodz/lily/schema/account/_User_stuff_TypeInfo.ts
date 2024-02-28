import { INT } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
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

    get name() { return "net.bodz.lily.schema.account.User"; }
    get icon() { return "fa-tag"; }
    get label() { return "User (Account)"; }
    get description() { return "User Account"; }

    static FIELD_TYPE_ID = "type";
    static FIELD_PRIMARY_GROUP_ID = "gid0";
    static FIELD_REFERER_ID = "referer";
    static FIELD_PERSON_ID = "person";

    static N_TYPE_ID = 10;
    static N_PRIMARY_GROUP_ID = 10;
    static N_REFERER_ID = 10;
    static N_PERSON_ID = 10;

    validators = new _User_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {

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
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _User_stuff_TypeInfo;
