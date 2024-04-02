import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import VoteRecordTypeInfo from "../../concrete/VoteRecordTypeInfo";
import { PostTalk_TYPE } from "./PostTalkTypeInfo";
import _PostTalkVote_stuff_Validators from "./_PostTalkVote_stuff_Validators";

export class _PostTalkVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_msg_vote";

    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_VOTE_SCORE = "votes";

    static readonly N_PARENT_ID = 19;
    static readonly N_VOTE_SCORE = 10;

    readonly validators = new _PostTalkVote_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTalkVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

            parent: property({ type: PostTalk_TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PostTalkVote_stuff_TypeInfo();

}

export default _PostTalkVote_stuff_TypeInfo;

export const _PostTalkVote_stuff_TYPE = _PostTalkVote_stuff_TypeInfo.INSTANCE;
