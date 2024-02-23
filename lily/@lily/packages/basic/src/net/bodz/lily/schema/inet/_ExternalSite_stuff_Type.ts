import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ExternalSite } from "./ExternalSite";
import ExternalSiteValidators from "./ExternalSiteValidators";

export class _ExternalSite_stuff_Type extends CoEntityType {

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

    static validators = new ExternalSiteValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        urlfmt: property({ type: "string", precision: 200, validator: this.validators.validateUrlfmt }),
        bonus: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateBonus }),
        count: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateCount }),

        parent: property({ type: ExternalSite.TYPE, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_ExternalSite_stuff_Type.declaredProperty);
    }

}

export default _ExternalSite_stuff_Type;
