import { property } from "@skeljs/dba/src/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/lily/entity";

import type { PartyType } from "./PartyType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Person_stuff_Type extends PartyType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "person";

    name = "net.bodz.lily.schema.contact.Person"
    icon = "fa-tag"

    static const FIELD_FATHER_ID = "father";
    static const FIELD_MOTHER_ID = "mother";
    static const FIELD_ROLE_COUNT = "nrole";
    static const FIELD_EMPLOYEE = "employee";
    static const FIELD_BANK_COUNT = "nbank";
    static const FIELD_GENDER = "gender";
    static const FIELD_HOMELAND = "homeland";
    static const FIELD_PASSPORT = "passport";
    static const FIELD_SSN = "ssn";
    static const FIELD_DLN = "dln";

    static const N_FATHER_ID = 10;
    static const N_MOTHER_ID = 10;
    static const N_ROLE_COUNT = 10;
    static const N_EMPLOYEE = 1;
    static const N_BANK_COUNT = 10;
    static const N_GENDER = 1;
    static const N_HOMELAND = 10;
    static const N_PASSPORT = 20;
    static const N_SSN = 20;
    static const N_DLN = 20;

    static declaredProperty: EntityPropertyMap = {
        roleCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_roleCount }),
        employee: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_employee }),
        bankCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_bankCount }),
        gender: property({ type: "string", precision: 1, validator: validators.validate_gender }),
        homeland: property({ type: "string", precision: 10, validator: validators.validate_homeland }),
        passport: property({ type: "string", precision: 20, validator: validators.validate_passport }),
        ssn: property({ type: "string", precision: 20, validator: validators.validate_ssn }),
        dln: property({ type: "string", precision: 20, validator: validators.validate_dln }),

        mother: property({ type: "net.bodz.lily.schema.contact.Person", validator: validators.validate_mother }),
        motherId: property({ type: "integer", precision: 10, validator: validators.validate_motherId }),

        father: property({ type: "net.bodz.lily.schema.contact.Person", validator: validators.validate_father }),
        fatherId: property({ type: "integer", precision: 10, validator: validators.validate_fatherId }),
    }

    constructor() {
        super();
        this.declare(_Person_stuff_Type.declaredProperty);
    }

}
