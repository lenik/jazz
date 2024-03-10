import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import ApiType from "./ApiType";
import VApp from "./VApp";
import _VApi_stuff_Validators from "./_VApi_stuff_Validators";

export class _VApi_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vapi";

    static readonly FIELD_ID = "id";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_APP_ID = "app";
    static readonly FIELD_API_ID = "api";
    static readonly FIELD_CALLBACK = "callback";

    static readonly N_ID = 19;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_APP_ID = 10;
    static readonly N_API_ID = 10;
    static readonly N_CALLBACK = 200;

    readonly validators = new _VApi_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.vapp.VApi"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            callback: property({ type: STRING, precision: 200, validator: this.validators.validateCallback }),

            api: property({ type: ApiType.TYPE, nullable: false, validator: this.validators.validateApi }),
            apiId: property({ type: INT, nullable: false, precision: 10 }),

            app: property({ type: VApp.TYPE, nullable: false, validator: this.validators.validateApp }),
            appId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _VApi_stuff_TypeInfo();

}

export default _VApi_stuff_TypeInfo;
