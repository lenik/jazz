import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import OrgUnit from "./OrgUnit";
import Organization from "./Organization";
import Person from "./Person";
import _PersonRole_stuff_Validators from "./_PersonRole_stuff_Validators";

export class _PersonRole_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "personrole";

    static readonly FIELD_ID = "id";
    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_ORG_UNIT_ID = "ou";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ROLE = "role";

    static readonly N_ID = 10;
    static readonly N_ORG_ID = 10;
    static readonly N_ORG_UNIT_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_ROLE = 20;

    readonly validators = new _PersonRole_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            role: property({ type: STRING, precision: 20, validator: this.validators.validateRole }),

            orgUnit: property({ type: OrgUnit.TYPE, validator: this.validators.validateOrgUnit }),
            orgUnitId: property({ type: INT, precision: 10 }),

            person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
            personId: property({ type: INT, nullable: false, precision: 10 }),

            org: property({ type: Organization.TYPE, nullable: false, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PersonRole_stuff_TypeInfo();

}

export default _PersonRole_stuff_TypeInfo;
