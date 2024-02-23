import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipalType from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Person } from "../contact/Person";
import { Group } from "./Group";
import { User } from "./User";
import { UserType } from "./UserType";
import UserValidators from "./UserValidators";

export class _User_stuff_Type extends CoPrincipalType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user";

    name = "net.bodz.lily.schema.account.User"
    icon = "fa-tag"
    label = "User (Account)"
    description = "User Account"

    static FIELD_TYPE_ID = "type";
    static FIELD_PRIMARY_GROUP_ID = "gid0";
    static FIELD_REFERER_ID = "referer";
    static FIELD_PERSON_ID = "person";

    static N_TYPE_ID = 10;
    static N_PRIMARY_GROUP_ID = 10;
    static N_REFERER_ID = 10;
    static N_PERSON_ID = 10;

    static validators = new UserValidators();

    static declaredProperty: EntityPropertyMap = {

        person: property({ type: Person.TYPE, validator: this.validators.validatePerson }),
        personId: property({ type: "integer", precision: 10 }),

        primaryGroup: property({ type: Group.TYPE, nullable: false, 
            description: "The primary user group, the default value of ownerGroup.", 
            validator: this.validators.validatePrimaryGroup }),
        primaryGroupId: property({ type: "integer", nullable: false, precision: 10, 
            description: "The primary user group, the default value of ownerGroup." }),

        referer: property({ type: User.TYPE, 
            description: "The referer user (used for promotion)", 
            validator: this.validators.validateReferer }),
        refererId: property({ type: "integer", precision: 10, 
            description: "The referer user (used for promotion)" }),

        type: property({ type: UserType.TYPE, nullable: false, 
            description: "User type like system-user, normal-user, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "User type like system-user, normal-user, etc." }),
    }

    constructor() {
        super();
        this.declare(_User_stuff_Type.declaredProperty);
    }

}

export default _User_stuff_Type;
