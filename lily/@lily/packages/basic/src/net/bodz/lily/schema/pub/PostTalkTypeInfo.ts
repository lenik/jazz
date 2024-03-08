import PostTalkValidators from "./PostTalkValidators";
import _PostTalk_stuff_TypeInfo from "./_PostTalk_stuff_TypeInfo";

export class PostTalkTypeInfo extends _PostTalk_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTalk"; }
    get icon() { return "fa-tag"; }

    validators = new PostTalkValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostTalkTypeInfo;
