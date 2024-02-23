import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Person } from "./Person";
import { PersonTagType } from "./PersonTagType";
import PersonTagValidators from "./PersonTagValidators";

export class _PersonTag_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person_tag";

    name = "net.bodz.lily.schema.contact.PersonTag"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_PERSON_ID = "person";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_PERSON_ID = 10;
    static N_TAG_ID = 10;

    static validators = new PersonTagValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),

        tag: property({ type: PersonTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
        tagId: property({ type: "integer", nullable: false, precision: 10 }),

        person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
        personId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PersonTag_stuff_Type.declaredProperty);
    }

}

export default _PersonTag_stuff_Type;
