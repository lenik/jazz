import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import VoteRecordTypeInfo from "../../concrete/VoteRecordTypeInfo";
import Post from "./Post";
import _PostVote_stuff_Validators from "./_PostVote_stuff_Validators";

export class _PostVote_stuff_TypeInfo extends VoteRecordTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "post_vote";

    get name() { return "net.bodz.lily.schema.pub.PostVote"; }
    get icon() { return "fa-tag"; }

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    validators = new _PostVote_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            voteScore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

            parent: property({ type: Post.TYPE, nullable: false, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    constructor() {
        super();
    }

}

export default _PostVote_stuff_TypeInfo;
