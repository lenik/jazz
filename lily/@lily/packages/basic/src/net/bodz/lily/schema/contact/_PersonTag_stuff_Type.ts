
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _PersonTag_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "person_tag";

    name = "net.bodz.lily.schema.contact.PersonTag"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_PERSON_ID = "person";
    static const FIELD_TAG_ID = "tag";

    static const N_ID = 10;
    static const N_PERSON_ID = 10;
    static const N_TAG_ID = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),

        tag: property({ type: "net.bodz.lily.schema.contact.PersonTagType", nullable: false, validator: validators.validate_tag }),
        tagId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_tagId }),

        person: property({ type: "net.bodz.lily.schema.contact.Person", nullable: false, validator: validators.validate_person }),
        personId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_personId }),
    }

    constructor() {
        super();
        this.declare(_PersonTag_stuff_Type.declaredProperty);
    }

}
