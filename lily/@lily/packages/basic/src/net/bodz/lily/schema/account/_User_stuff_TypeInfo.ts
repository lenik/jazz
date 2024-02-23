import type { integer } from "@skeljs/core/src/lang/type";
import CoPrincipalTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoPrincipalTypeInfo";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _User_stuff_Validators from "./_User_stuff_Validators";

export class _User_stuff_TypeInfo extends CoPrincipalTypeInfo {

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

    static validators = new _User_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {

        person: property({ type: net.bodz.lily.schema.contact.PersonTypeInfo, validator: this.validators.validatePerson }),
        personId: property({ type: "integer", precision: 10 }),

        primaryGroup: property({ type: net.bodz.lily.schema.account.GroupTypeInfo, nullable: false, 
            description: "The primary user group, the default value of ownerGroup.", 
            validator: this.validators.validatePrimaryGroup }),
        primaryGroupId: property({ type: "integer", nullable: false, precision: 10, 
            description: "The primary user group, the default value of ownerGroup." }),

        referer: property({ type: net.bodz.lily.schema.account.UserTypeInfo, 
            description: "The referer user (used for promotion)", 
            validator: this.validators.validateReferer }),
        refererId: property({ type: "integer", precision: 10, 
            description: "The referer user (used for promotion)" }),

        type: property({ type: net.bodz.lily.schema.account.UserTypeTypeInfo, nullable: false, 
            description: "User type like system-user, normal-user, etc.", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "User type like system-user, normal-user, etc." }),
    }

    constructor() {
        super();
        this.declare(_User_stuff_TypeInfo.declaredProperty);
    }

}

export default _User_stuff_TypeInfo;
