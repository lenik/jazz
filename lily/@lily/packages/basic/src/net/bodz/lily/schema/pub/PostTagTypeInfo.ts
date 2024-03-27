import PostTag from "./PostTag";
import PostTagValidators from "./PostTagValidators";
import _PostTag_stuff_TypeInfo from "./_PostTag_stuff_TypeInfo";

export class PostTagTypeInfo extends _PostTag_stuff_TypeInfo {

    readonly validators = new PostTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostTagTypeInfo();

}

export default PostTagTypeInfo;
