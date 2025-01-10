import { INT } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import IdEntityTypeInfo from "../../concrete/IdEntityTypeInfo";
import { PersonTagType_TYPE } from "./PersonTagTypeTypeInfo";
import { Person_TYPE } from "./PersonTypeInfo";
import _PersonTag_stuff_Validators from "./_PersonTag_stuff_Validators";

export class _PersonTag_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "person_tag";

    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_TAG_ID = "tag";

    static readonly N_PERSON_ID = 10;
    static readonly N_TAG_ID = 10;

    readonly validators = new _PersonTag_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.contact.PersonTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            tag: property({ type: PersonTagType_TYPE, nullable: false, validator: this.validators.validateTag }),
            tagId: property({ type: INT, nullable: false, precision: 10 }),

            person: property({ type: Person_TYPE, nullable: false, validator: this.validators.validatePerson }),
            personId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PersonTag_stuff_TypeInfo();

}

export default _PersonTag_stuff_TypeInfo;

export const _PersonTag_stuff_TYPE = _PersonTag_stuff_TypeInfo.INSTANCE;
