import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_Type from "./_PostTalkVote_stuff_Type";

// Type Info

export class PostTalkVoteType extends _PostTalkVote_stuff_Type {

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    static validators = new PostTalkVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkVoteType.declaredProperty);
    }

}

export default PostTalkVote;
