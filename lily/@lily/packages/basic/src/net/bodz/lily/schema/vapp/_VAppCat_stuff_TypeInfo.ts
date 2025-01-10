import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoCategoryTypeInfo from "../../concrete/CoCategoryTypeInfo";
import _VAppCat_stuff_Validators from "./_VAppCat_stuff_Validators";

export class _VAppCat_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappcat";

    get name() { return "net.bodz.lily.schema.vapp.VAppCat"; }
    get icon() { return "fa-tag"; }

    static FIELD_NAME = "name";

    static N_NAME = 30;

    validators = new _VAppCat_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

    constructor(selfType: any) {
        super(selfType, INT);
    }

}

export default _VAppCat_stuff_TypeInfo;
