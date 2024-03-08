import PostTagTypeValidators from "./PostTagTypeValidators";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class PostTagTypeTypeInfo extends _PostTagType_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostTagType"; }
    get icon() { return "fa-tag"; }

    validators = new PostTagTypeValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostTagTypeTypeInfo;
