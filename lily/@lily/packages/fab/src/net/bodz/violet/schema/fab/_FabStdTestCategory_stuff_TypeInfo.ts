import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoCategoryTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryTypeInfo";

import _FabStdTestCategory_stuff_Validators from "./_FabStdTestCategory_stuff_Validators";

export class _FabStdTestCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtestcat";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _FabStdTestCategory_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTestCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

}

export default _FabStdTestCategory_stuff_TypeInfo;

export const _FabStdTestCategory_stuff_TYPE = _FabStdTestCategory_stuff_TypeInfo.INSTANCE;
