import { DOUBLE, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import PlanCategory from "./PlanCategory";
import PlanPhase from "./PlanPhase";
import _Plan_stuff_Validators from "./_Plan_stuff_Validators";

export class _Plan_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plan";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_READ_COUNT = "nread";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_NLIKE = "nlike";
    static readonly FIELD_VALUE = "value";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_READ_COUNT = 10;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_NLIKE = 10;
    static readonly N_VALUE = 17;

    readonly validators = new _Plan_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.Plan"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            readCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateReadCount }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            nlike: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateNlike }),
            value: property({ type: DOUBLE, nullable: false, precision: 17, scale: 17, validator: this.validators.validateValue }),

            category: property({ type: PlanCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            phase: property({ type: PlanPhase.TYPE, nullable: false, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Plan_stuff_TypeInfo();

}

export default _Plan_stuff_TypeInfo;
