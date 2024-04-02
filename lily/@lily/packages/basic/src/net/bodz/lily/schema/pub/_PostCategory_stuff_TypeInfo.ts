import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoCategoryTypeInfo from "../../concrete/CoCategoryTypeInfo";
import _PostCategory_stuff_Validators from "./_PostCategory_stuff_Validators";

export class _PostCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "postcat";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _PostCategory_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.pub.PostCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

}

export default _PostCategory_stuff_TypeInfo;

export const _PostCategory_stuff_TYPE = _PostCategory_stuff_TypeInfo.INSTANCE;
