import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _PersonRole_stuff_Validators from "./_PersonRole_stuff_Validators";

export class _PersonRole_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "personrole";

    name = "net.bodz.lily.schema.contact.PersonRole"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_ORG_ID = "org";
    static FIELD_ORG_UNIT_ID = "ou";
    static FIELD_PERSON_ID = "person";
    static FIELD_ROLE = "role";

    static N_ID = 10;
    static N_ORG_ID = 10;
    static N_ORG_UNIT_ID = 10;
    static N_PERSON_ID = 10;
    static N_ROLE = 20;

    static validators = new _PersonRole_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        role: property({ type: "string", precision: 20, validator: this.validators.validateRole }),

        orgUnit: property({ type: net.bodz.lily.schema.contact.OrgUnitTypeInfo, validator: this.validators.validateOrgUnit }),
        orgUnitId: property({ type: "integer", precision: 10 }),

        person: property({ type: net.bodz.lily.schema.contact.PersonTypeInfo, nullable: false, validator: this.validators.validatePerson }),
        personId: property({ type: "integer", nullable: false, precision: 10 }),

        org: property({ type: net.bodz.lily.schema.contact.OrganizationTypeInfo, nullable: false, validator: this.validators.validateOrg }),
        orgId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PersonRole_stuff_TypeInfo.declaredProperty);
    }

}

export default _PersonRole_stuff_TypeInfo;
