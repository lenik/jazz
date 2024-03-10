import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoNodeTypeInfo from "../../concrete/CoNodeTypeInfo";
import _ExternalSite_stuff_Validators from "./_ExternalSite_stuff_Validators";

export class _ExternalSite_stuff_TypeInfo extends CoNodeTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "extsite";

    static readonly FIELD_URLFMT = "urlfmt";
    static readonly FIELD_BONUS = "bonus";
    static readonly FIELD_COUNT = "count";

    static readonly N_URLFMT = 200;
    static readonly N_BONUS = 10;
    static readonly N_COUNT = 10;

    readonly validators = new _ExternalSite_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.inet.ExternalSite"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            urlfmt: property({ type: STRING, precision: 200, validator: this.validators.validateUrlfmt }),
            bonus: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateBonus }),
            count: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateCount }),
        });
    }

}

export default _ExternalSite_stuff_TypeInfo;
