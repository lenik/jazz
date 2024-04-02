import PostBackref from "./PostBackref";
import PostBackrefValidators from "./PostBackrefValidators";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class PostBackrefTypeInfo extends _PostBackref_stuff_TypeInfo {

    readonly validators = new PostBackrefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostBackref"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostBackref();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostBackrefTypeInfo();

}

export default PostBackrefTypeInfo;

export const PostBackref_TYPE = PostBackrefTypeInfo.INSTANCE;
