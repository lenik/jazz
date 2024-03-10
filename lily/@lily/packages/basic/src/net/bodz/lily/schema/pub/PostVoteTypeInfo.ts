import PostVoteValidators from "./PostVoteValidators";
import _PostVote_stuff_TypeInfo from "./_PostVote_stuff_TypeInfo";

export class PostVoteTypeInfo extends _PostVote_stuff_TypeInfo {

    readonly validators = new PostVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostVoteTypeInfo();

}

export default PostVoteTypeInfo;
