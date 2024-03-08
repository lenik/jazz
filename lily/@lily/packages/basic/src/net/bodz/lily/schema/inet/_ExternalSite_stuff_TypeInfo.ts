import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ExternalSite from "./ExternalSite";
import _ExternalSite_stuff_Validators from "./_ExternalSite_stuff_Validators";

export class _ExternalSite_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "extsite";

    get name() { return "net.bodz.lily.schema.inet.ExternalSite"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";
    static FIELD_URLFMT = "urlfmt";
    static FIELD_BONUS = "bonus";
    static FIELD_COUNT = "count";

    static N_ID = 10;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;
    static N_URLFMT = 200;
    static N_BONUS = 10;
    static N_COUNT = 10;

    validators = new _ExternalSite_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            urlfmt: property({ type: STRING, precision: 200, validator: this.validators.validateUrlfmt }),
            bonus: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateBonus }),
            count: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateCount }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ExternalSite_stuff_TypeInfo;
