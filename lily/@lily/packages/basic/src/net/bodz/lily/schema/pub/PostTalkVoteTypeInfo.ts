import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class PostTalkVoteTypeInfo extends _PostTalkVote_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTalkVote"; }
    get icon() { return "fa-tag"; }

    validators = new PostTalkVoteValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostTalkVoteTypeInfo;
