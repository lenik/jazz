import PostParameter from "./PostParameter";
import PostParameterValidators from "./PostParameterValidators";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class PostParameterTypeInfo extends _PostParameter_stuff_TypeInfo {

    readonly validators = new PostParameterValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostParameter"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostParameter();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostParameterTypeInfo();

}

export default PostParameterTypeInfo;

export const PostParameter_TYPE = PostParameterTypeInfo.INSTANCE;
