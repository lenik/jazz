import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ZoneCategoryTypeInfo from "./ZoneCategoryTypeInfo";
import ZoneTypeInfo from "./ZoneTypeInfo";
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

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 10, validator: this.validators.validateCode }),
        country: property({ type: "string", precision: 2, validator: this.validators.validateCountry }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        telCode: property({ type: "string", precision: 10, validator: this.validators.validateTelCode }),
        postCode: property({ type: "string", precision: 10, validator: this.validators.validatePostCode }),
        data: property({ type: "JsonVariant", validator: this.validators.validateData }),

        parent: property({ type: ZoneTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "int", precision: 10 }),

        category: property({ type: ZoneCategoryTypeInfo, nullable: false, validator: this.validators.validateCategory }),
        categoryId: property({ type: "int", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _Zone_stuff_TypeInfo;
