import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import OrgUnitTypeInfo from "./OrgUnitTypeInfo";
import OrganizationTypeInfo from "./OrganizationTypeInfo";
import PersonTypeInfo from "./PersonTypeInfo";
import _PersonRole_stuff_Validators from "./_PersonRole_stuff_Validators";

export class _PersonRole_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "personrole";

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }

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

    validators = new _PersonRole_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        role: property({ type: "string", precision: 20, validator: this.validators.validateRole }),

        orgUnit: property({ type: OrgUnitTypeInfo, validator: this.validators.validateOrgUnit }),
        orgUnitId: property({ type: "int", precision: 10 }),

        person: property({ type: PersonTypeInfo, nullable: false, validator: this.validators.validatePerson }),
        personId: property({ type: "int", nullable: false, precision: 10 }),

        org: property({ type: OrganizationTypeInfo, nullable: false, validator: this.validators.validateOrg }),
        orgId: property({ type: "int", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PersonRole_stuff_TypeInfo;
