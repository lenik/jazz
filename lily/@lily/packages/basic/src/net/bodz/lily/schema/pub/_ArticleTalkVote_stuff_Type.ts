import type { integer, long } from "@skeljs/core/src/lang/type";
import VoteRecordType from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordType";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { ArticleTalk } from "./ArticleTalk";
import ArticleTalkVoteValidators from "./ArticleTalkVoteValidators";

export class _ArticleTalkVote_stuff_Type extends VoteRecordType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "article_msg_vote";

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    static FIELD_PARENT_ID = "parent";
    static FIELD_VOTE_SCORE = "votes";

    static N_PARENT_ID = 19;
    static N_VOTE_SCORE = 10;

    static validators = new ArticleTalkVoteValidators();

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVoteScore }),

        parent: property({ type: ArticleTalk.TYPE, nullable: false, validator: this.validators.validateParent }),
        parentId: property({ type: "long", nullable: false, precision: 19 }),
    }

    constructor() {
        super();
        this.declare(_ArticleTalkVote_stuff_Type.declaredProperty);
    }

}

export default _ArticleTalkVote_stuff_Type;
