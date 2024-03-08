import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import Zone from "./Zone";
import ZoneCategory from "./ZoneCategory";
import _Zone_stuff_Validators from "./_Zone_stuff_Validators";

export class _Zone_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "zone";

    get name() { return "net.bodz.lily.schema.geo.Zone"; }
    get icon() { return "fa-tag"; }

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

    validators = new _Zone_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 10, validator: this.validators.validateCode }),
            country: property({ type: STRING, precision: 2, validator: this.validators.validateCountry }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            telCode: property({ type: STRING, precision: 10, validator: this.validators.validateTelCode }),
            postCode: property({ type: STRING, precision: 10, validator: this.validators.validatePostCode }),
            data: property({ type: JSON_VARIANT, validator: this.validators.validateData }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),

            category: property({ type: ZoneCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _Zone_stuff_TypeInfo;
