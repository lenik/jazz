import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import _ExternalSite_stuff_Validators from "./_ExternalSite_stuff_Validators";

export class _ExternalSite_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "extsite";

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

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

    static validators = new _ExternalSite_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        urlfmt: property({ type: "string", precision: 200, validator: this.validators.validateUrlfmt }),
        bonus: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateBonus }),
        count: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateCount }),

        parent: property({ type: net.bodz.lily.schema.inet.ExternalSiteTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ExternalSite_stuff_TypeInfo.declaredProperty);
    }

}

export default _ExternalSite_stuff_TypeInfo;
