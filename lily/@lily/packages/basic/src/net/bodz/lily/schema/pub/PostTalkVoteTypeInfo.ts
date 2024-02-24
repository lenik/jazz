import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class PostTalkVoteTypeInfo extends _PostTalkVote_stuff_TypeInfo {

    name = "net.bodz.lily.schema.pub.PostTalkVote"
    icon = "fa-tag"

    validators = new PostTalkVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTalkVoteTypeInfo;
