import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class PostTalkVoteTypeInfo extends _PostTalkVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTalkVote"; }
    get icon() { return "fa-tag"; }

    validators = new PostTalkVoteValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default PostTalkVoteTypeInfo;
