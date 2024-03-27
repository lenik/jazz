import Post from "./Post";
import PostValidators from "./PostValidators";
import _Post_stuff_TypeInfo from "./_Post_stuff_TypeInfo";

export class PostTypeInfo extends _Post_stuff_TypeInfo {

    readonly validators = new PostValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.Post"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Post();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostTypeInfo();

}

export default PostTypeInfo;
