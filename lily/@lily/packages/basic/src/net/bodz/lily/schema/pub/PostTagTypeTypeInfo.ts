import PostTagType from "./PostTagType";
import PostTagTypeValidators from "./PostTagTypeValidators";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class PostTagTypeTypeInfo extends _PostTagType_stuff_TypeInfo {

    readonly validators = new PostTagTypeValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostTagType"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostTagType();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostTagTypeTypeInfo();

}

export default PostTagTypeTypeInfo;
