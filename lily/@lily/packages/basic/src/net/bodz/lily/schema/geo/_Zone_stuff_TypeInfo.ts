import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import { ZoneCategory_TYPE } from "./ZoneCategoryTypeInfo";
import { Zone_TYPE } from "./ZoneTypeInfo";
import _Zone_stuff_Validators from "./_Zone_stuff_Validators";

export class _Zone_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "zone";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_COUNTRY = "country";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_DEPTH = "depth";
    static readonly FIELD_TEL_CODE = "telcode";
    static readonly FIELD_POST_CODE = "postcode";
    static readonly FIELD_DATA = "data";

    static readonly N_CODE = 10;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_COUNTRY = 2;
    static readonly N_PARENT_ID = 10;
    static readonly N_DEPTH = 10;
    static readonly N_TEL_CODE = 10;
    static readonly N_POST_CODE = 10;
    static readonly N_DATA = 2147483647;

    readonly validators = new _Zone_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.geo.Zone"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 10, validator: this.validators.validateCode }),
            country: property({ type: STRING, precision: 2, validator: this.validators.validateCountry }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            telCode: property({ type: STRING, precision: 10, validator: this.validators.validateTelCode }),
            postCode: property({ type: STRING, precision: 10, validator: this.validators.validatePostCode }),
            data: property({ type: JSON_VARIANT, validator: this.validators.validateData }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),

            category: property({ type: ZoneCategory_TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Zone_stuff_TypeInfo();

}

export default _Zone_stuff_TypeInfo;

export const _Zone_stuff_TYPE = _Zone_stuff_TypeInfo.INSTANCE;
