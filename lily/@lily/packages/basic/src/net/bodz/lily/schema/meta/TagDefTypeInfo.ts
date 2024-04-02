import TagDef from "./TagDef";
import TagDefValidators from "./TagDefValidators";
import _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

export class TagDefTypeInfo extends _TagDef_stuff_TypeInfo {

    readonly validators = new TagDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.TagDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Tag"; }

    override create() {
        return new TagDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TagDefTypeInfo();

}

export default TagDefTypeInfo;

export const TagDef_TYPE = TagDefTypeInfo.INSTANCE;
