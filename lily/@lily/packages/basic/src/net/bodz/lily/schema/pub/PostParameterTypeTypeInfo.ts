import PostParameterType from "./PostParameterType";
import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class PostParameterTypeTypeInfo extends _PostParameterType_stuff_TypeInfo {

    readonly validators = new PostParameterTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostParameterType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostParameterType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostParameterTypeTypeInfo();

}

export default PostParameterTypeTypeInfo;

export const PostParameterType_TYPE = PostParameterTypeTypeInfo.INSTANCE;
