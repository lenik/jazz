import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoParameterTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _PlanDoParameter_stuff_Validators from "./_PlanDoParameter_stuff_Validators";

export class _PlanDoParameter_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plandoparm";

    static readonly FIELD_NAME = "name";
    static readonly FIELD_DUMMY = "dummy";

    static readonly N_NAME = 30;
    static readonly N_DUMMY = 10;

    readonly validators = new _PlanDoParameter_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

}

export default _PlanDoParameter_stuff_TypeInfo;

export const _PlanDoParameter_stuff_TYPE = _PlanDoParameter_stuff_TypeInfo.INSTANCE;
