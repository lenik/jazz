import { INT, LONG } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import VoteRecordTypeInfo from "lily-basic/src/net/bodz/lily/concrete/VoteRecordTypeInfo";

import { PlanDo_TYPE } from "./PlanDoTypeInfo";
import _PlanDoVote_stuff_Validators from "./_PlanDoVote_stuff_Validators";

export class _PlanDoVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plando_vote";

    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_VOTE_SCORE = "votes";

    static readonly N_PARENT_ID = 19;
    static readonly N_VOTE_SCORE = 10;

    readonly validators = new _PlanDoVote_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

            parent: property({ type: PlanDo_TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PlanDoVote_stuff_TypeInfo();

}

export default _PlanDoVote_stuff_TypeInfo;

export const _PlanDoVote_stuff_TYPE = _PlanDoVote_stuff_TypeInfo.INSTANCE;
