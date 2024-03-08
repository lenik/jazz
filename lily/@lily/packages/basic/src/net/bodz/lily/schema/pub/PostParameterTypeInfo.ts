import PostParameterValidators from "./PostParameterValidators";
import _PostParameter_stuff_TypeInfo from "./_PostParameter_stuff_TypeInfo";

export class PostParameterTypeInfo extends _PostParameter_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostParameter"; }
    get icon() { return "fa-tag"; }

    validators = new PostParameterValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostParameterTypeInfo;
