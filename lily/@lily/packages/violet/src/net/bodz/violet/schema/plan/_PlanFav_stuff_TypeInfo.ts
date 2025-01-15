import { LONG } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "lily-basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import { Plan_TYPE } from "./PlanTypeInfo";
import _PlanFav_stuff_Validators from "./_PlanFav_stuff_Validators";

export class _PlanFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plan_fav";

    static readonly FIELD_PLAN_ID = "plan";

    static readonly N_PLAN_ID = 19;

    readonly validators = new _PlanFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            plan: property({ type: Plan_TYPE, nullable: false, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PlanFav_stuff_TypeInfo();

}

export default _PlanFav_stuff_TypeInfo;

export const _PlanFav_stuff_TYPE = _PlanFav_stuff_TypeInfo.INSTANCE;
