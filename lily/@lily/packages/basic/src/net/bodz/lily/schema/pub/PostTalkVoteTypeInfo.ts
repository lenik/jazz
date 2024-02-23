import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

// Type Info

export class PostTalkVoteTypeInfo extends _PostTalkVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    static validators = new PostTalkVoteValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(PostTalkVoteTypeInfo.declaredProperty);
    }

}

export default PostTalkVote;
