import PostParameterTypeValidators from "./PostParameterTypeValidators";
import _PostParameterType_stuff_TypeInfo from "./_PostParameterType_stuff_TypeInfo";

export class PostParameterTypeTypeInfo extends _PostParameterType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostParameterType"; }
    get icon() { return "fa-tag"; }

    validators = new PostParameterTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostParameterTypeTypeInfo;
