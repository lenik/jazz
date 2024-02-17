
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { PartyType } from "./PartyType";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Organization_stuff_Type extends PartyType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "org";

    name = "net.bodz.lily.schema.contact.Organization"
    icon = "fa-tag"

    static const FIELD_ROLE_COUNT = "nrole";
    static const FIELD_BANK_COUNT = "nbank";
    static const FIELD_SIZE = "size";
    static const FIELD_TAX_ID = "taxid";

    static const N_ROLE_COUNT = 10;
    static const N_BANK_COUNT = 10;
    static const N_SIZE = 10;
    static const N_TAX_ID = 20;

    static declaredProperty: EntityPropertyMap = {
        roleCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_roleCount }),
        bankCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_bankCount }),
        size: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_size }),
        taxId: property({ type: "string", precision: 20, validator: validators.validate_taxId }),
    }

    constructor() {
        super();
        this.declare(_Organization_stuff_Type.declaredProperty);
    }

}
