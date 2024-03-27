import PostTalk from "./PostTalk";
import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class PostTalkTypeInfo extends _PostTalk_stuff_TypeInfo {

    readonly validators = new PostTalkValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTalk"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostTalk();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostTalkTypeInfo();

}

export default PostTalkTypeInfo;
