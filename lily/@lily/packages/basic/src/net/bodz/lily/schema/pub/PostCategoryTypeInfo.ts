import PostCategory from "./PostCategory";
import PostCategoryValidators from "./PostCategoryValidators";
import _PostCategory_stuff_TypeInfo from "./_PostCategory_stuff_TypeInfo";

export class PostCategoryTypeInfo extends _PostCategory_stuff_TypeInfo {

    readonly validators = new PostCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.pub.PostCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new PostCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new PostCategoryTypeInfo();

}

export default PostCategoryTypeInfo;

export const PostCategory_TYPE = PostCategoryTypeInfo.INSTANCE;
