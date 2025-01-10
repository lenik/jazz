import { INT } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoCodeTypeInfo from "../../concrete/CoCodeTypeInfo";
import _SchemaDef_stuff_Validators from "./_SchemaDef_stuff_Validators";

export class _SchemaDef_stuff_TypeInfo extends CoCodeTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_schema";

    static readonly FIELD_DUMMY = "dummy";

    static readonly N_DUMMY = 10;

    readonly validators = new _SchemaDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.SchemaDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Schema"; }

    override preamble() {
        super.preamble();
        this.declare({
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

}

export default _SchemaDef_stuff_TypeInfo;

export const _SchemaDef_stuff_TYPE = _SchemaDef_stuff_TypeInfo.INSTANCE;
