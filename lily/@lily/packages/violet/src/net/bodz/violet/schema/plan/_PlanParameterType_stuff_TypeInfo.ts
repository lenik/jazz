import { STRING } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoParameterTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _PlanParameterType_stuff_Validators from "./_PlanParameterType_stuff_Validators";

export class _PlanParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "planparm";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _PlanParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

}

export default _PlanParameterType_stuff_TypeInfo;

export const _PlanParameterType_stuff_TYPE = _PlanParameterType_stuff_TypeInfo.INSTANCE;
