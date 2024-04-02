import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoCodeTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoCodeTypeInfo";

import _RegionLevel_stuff_Validators from "./_RegionLevel_stuff_Validators";

export class _RegionLevel_stuff_TypeInfo extends CoCodeTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "regionlevel";

    static readonly FIELD_DUMMY = "dummy";

    static readonly N_DUMMY = 10;

    readonly validators = new _RegionLevel_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.RegionLevel"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

}

export default _RegionLevel_stuff_TypeInfo;

export const _RegionLevel_stuff_TYPE = _RegionLevel_stuff_TypeInfo.INSTANCE;
