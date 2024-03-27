import PostTalkVote from "./PostTalkVote";
import PostTalkVoteValidators from "./PostTalkVoteValidators";
import _PostTalkVote_stuff_TypeInfo from "./_PostTalkVote_stuff_TypeInfo";

export class PostTalkVoteTypeInfo extends _PostTalkVote_stuff_TypeInfo {

    readonly validators = new PostTalkVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTalkVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostTalkVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostTalkVoteTypeInfo();

}

export default PostTalkVoteTypeInfo;
