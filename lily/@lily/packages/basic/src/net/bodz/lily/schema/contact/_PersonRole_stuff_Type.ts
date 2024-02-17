
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _PersonRole_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "personrole";

    name = "net.bodz.lily.schema.contact.PersonRole"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_ORG_ID = "org";
    static const FIELD_ORG_UNIT_ID = "ou";
    static const FIELD_PERSON_ID = "person";
    static const FIELD_ROLE = "role";

    static const N_ID = 10;
    static const N_ORG_ID = 10;
    static const N_ORG_UNIT_ID = 10;
    static const N_PERSON_ID = 10;
    static const N_ROLE = 20;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        role: property({ type: "string", precision: 20, validator: validators.validate_role }),

        orgUnit: property({ type: "net.bodz.lily.schema.contact.OrgUnit", validator: validators.validate_orgUnit }),
        orgUnitId: property({ type: "integer", precision: 10, validator: validators.validate_orgUnitId }),

        person: property({ type: "net.bodz.lily.schema.contact.Person", nullable: false, validator: validators.validate_person }),
        personId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_personId }),

        org: property({ type: "net.bodz.lily.schema.contact.Organization", nullable: false, validator: validators.validate_org }),
        orgId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_orgId }),
    }

    constructor() {
        super();
        this.declare(_PersonRole_stuff_Type.declaredProperty);
    }

}
