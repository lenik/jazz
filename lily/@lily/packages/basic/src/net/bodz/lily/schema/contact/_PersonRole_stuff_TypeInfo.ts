import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { OrgUnit_TYPE } from "./OrgUnitTypeInfo";
import { Organization_TYPE } from "./OrganizationTypeInfo";
import { Person_TYPE } from "./PersonTypeInfo";
import _PersonRole_stuff_Validators from "./_PersonRole_stuff_Validators";

export class _PersonRole_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "personrole";

    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_ORG_UNIT_ID = "ou";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ROLE = "role";

    static readonly N_ORG_ID = 10;
    static readonly N_ORG_UNIT_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_ROLE = 20;

    readonly validators = new _PersonRole_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.contact.PersonRole"; }
    get icon() { return "fa-tag"; }
    get description() { return "职位关联"; }

    override preamble() {
        super.preamble();
        this.declare({
            role: property({ type: STRING, precision: 20, validator: this.validators.validateRole }),

            orgUnit: property({ type: OrgUnit_TYPE, validator: this.validators.validateOrgUnit }),
            orgUnitId: property({ type: INT, precision: 10 }),

            person: property({ type: Person_TYPE, nullable: false, validator: this.validators.validatePerson }),
            personId: property({ type: INT, nullable: false, precision: 10 }),

            org: property({ type: Organization_TYPE, nullable: false, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PersonRole_stuff_TypeInfo();

}

export default _PersonRole_stuff_TypeInfo;

export const _PersonRole_stuff_TYPE = _PersonRole_stuff_TypeInfo.INSTANCE;
