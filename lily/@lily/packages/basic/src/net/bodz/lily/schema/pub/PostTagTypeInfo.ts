import PostTagValidators from "./PostTagValidators";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class PostTagTypeInfo extends _PostTag_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTag"; }
    get icon() { return "fa-tag"; }

    validators = new PostTagValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostTagTypeInfo;
