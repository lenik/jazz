import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { OrgUnit } from "./OrgUnit";
import { Organization } from "./Organization";
import { Person } from "./Person";
import PersonRoleValidators from "./PersonRoleValidators";

export class _PersonRole_stuff_Type extends CoEntityType {

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

    static validators = new PersonRoleValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        role: property({ type: "string", precision: 20, validator: this.validators.validateRole }),

        orgUnit: property({ type: OrgUnit.TYPE, validator: this.validators.validateOrgUnit }),
        orgUnitId: property({ type: "integer", precision: 10 }),

        person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
        personId: property({ type: "integer", nullable: false, precision: 10 }),

        org: property({ type: Organization.TYPE, nullable: false, validator: this.validators.validateOrg }),
        orgId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PersonRole_stuff_Type.declaredProperty);
    }

}

export default _PersonRole_stuff_Type;
