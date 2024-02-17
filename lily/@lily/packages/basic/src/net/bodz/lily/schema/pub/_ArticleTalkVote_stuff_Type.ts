
import type { VoteRecordType } from "@skeljs/dba/src/net/bodz/lily/concrete/VoteRecordType";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import { * as validators } from "./PersonValidators";

// Type Info

export class _ArticleTalkVote_stuff_Type extends VoteRecordType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "article_msg_vote";

    name = "net.bodz.lily.schema.pub.ArticleTalkVote"
    icon = "fa-tag"

    static const FIELD_PARENT_ID = "parent";
    static const FIELD_VOTE_SCORE = "votes";

    static const N_PARENT_ID = 19;
    static const N_VOTE_SCORE = 10;

    static declaredProperty: EntityPropertyMap = {
        voteScore: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_voteScore }),

        parent: property({ type: "net.bodz.lily.schema.pub.ArticleTalk", nullable: false, validator: validators.validate_parent }),
        parentId: property({ type: "long", nullable: false, precision: 19, validator: validators.validate_parentId }),
    }

    constructor() {
        super();
        this.declare(_ArticleTalkVote_stuff_Type.declaredProperty);
    }

}
