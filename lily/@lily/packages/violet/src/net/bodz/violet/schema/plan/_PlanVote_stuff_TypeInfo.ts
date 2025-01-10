import { INT, LONG } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import VoteRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/VoteRecordTypeInfo";

import { Plan_TYPE } from "./PlanTypeInfo";
import _PlanVote_stuff_Validators from "./_PlanVote_stuff_Validators";

export class _PlanVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plan_vote";

    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_VOTE_SCORE = "votes";

    static readonly N_PARENT_ID = 19;
    static readonly N_VOTE_SCORE = 10;

    readonly validators = new _PlanVote_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

            parent: property({ type: Plan_TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PlanVote_stuff_TypeInfo();

}

export default _PlanVote_stuff_TypeInfo;

export const _PlanVote_stuff_TYPE = _PlanVote_stuff_TypeInfo.INSTANCE;
