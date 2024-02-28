import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Person from "./Person";
import PersonTagType from "./PersonTagType";
import _PersonTag_stuff_Validators from "./_PersonTag_stuff_Validators";

export class _PersonTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person_tag";

    get name() { return "net.bodz.lily.schema.contact.PersonTag"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_PERSON_ID = "person";
    static FIELD_TAG_ID = "tag";

    static N_ID = 10;
    static N_PERSON_ID = 10;
    static N_TAG_ID = 10;

    validators = new _PersonTag_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

        tag: property({ type: PersonTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
        tagId: property({ type: INT, nullable: false, precision: 10 }),

        person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
        personId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PersonTag_stuff_TypeInfo;
