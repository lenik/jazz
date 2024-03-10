import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Person from "./Person";
import PersonTagType from "./PersonTagType";
import _PersonTag_stuff_Validators from "./_PersonTag_stuff_Validators";

export class _PersonTag_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person_tag";

    static readonly FIELD_ID = "id";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_TAG_ID = "tag";

    static readonly N_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_TAG_ID = 10;

    readonly validators = new _PersonTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.contact.PersonTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),

            tag: property({ type: PersonTagType.TYPE, nullable: false, validator: this.validators.validateTag }),
            tagId: property({ type: INT, nullable: false, precision: 10 }),

            person: property({ type: Person.TYPE, nullable: false, validator: this.validators.validatePerson }),
            personId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PersonTag_stuff_TypeInfo();

}

export default _PersonTag_stuff_TypeInfo;
