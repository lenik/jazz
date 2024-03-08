import PostBackrefValidators from "./PostBackrefValidators";
import _PostBackref_stuff_TypeInfo from "./_PostBackref_stuff_TypeInfo";

export class PostBackrefTypeInfo extends _PostBackref_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.pub.PostBackref"; }
    get icon() { return "fa-tag"; }

    validators = new PostBackrefValidators(this);

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    constructor() {
        super();
    }

}

export default PostBackrefTypeInfo;
