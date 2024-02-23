import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { Zone } from "./Zone";
import { ZoneCategory } from "./ZoneCategory";
import ZoneValidators from "./ZoneValidators";

export class _Zone_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "zone";

    name = "net.bodz.lily.schema.geo.Zone"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_CATEGORY_ID = "cat";
    static FIELD_COUNTRY = "country";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";
    static FIELD_TEL_CODE = "telcode";
    static FIELD_POST_CODE = "postcode";
    static FIELD_DATA = "data";

    static N_ID = 10;
    static N_CODE = 10;
    static N_CATEGORY_ID = 10;
    static N_COUNTRY = 2;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;
    static N_TEL_CODE = 10;
    static N_POST_CODE = 10;
    static N_DATA = 2147483647;

    static validators = new ZoneValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 10, validator: this.validators.validateCode }),
        country: property({ type: "string", precision: 2, validator: this.validators.validateCountry }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        telCode: property({ type: "string", precision: 10, validator: this.validators.validateTelCode }),
        postCode: property({ type: "string", precision: 10, validator: this.validators.validatePostCode }),
        data: property({ type: "any", validator: this.validators.validateData }),

        parent: property({ type: Zone.TYPE, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),

        category: property({ type: ZoneCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
        categoryId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_Zone_stuff_Type.declaredProperty);
    }

}

export default _Zone_stuff_Type;
