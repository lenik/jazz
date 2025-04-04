import { BOOLEAN, LIST } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { TagDef_TYPE } from "./TagDefTypeInfo";
import TagGroupDef from "./TagGroupDef";
import TagGroupDefValidators from "./TagGroupDefValidators";
import _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class TagGroupDefTypeInfo extends _TagGroupDef_stuff_TypeInfo {

    readonly validators = new TagGroupDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.TagGroupDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override create() {
        return new TagGroupDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
            ortho: property({ type: BOOLEAN, nullable: false, 
                description: "Orthogonal", 
                validator: this.validators.validateOrtho }),
            tags: property({ type: LIST(TagDef_TYPE), 
                description: "Tag List", 
                validator: this.validators.validateTags }),
        });
    }

    static readonly INSTANCE = new TagGroupDefTypeInfo();

}

export default TagGroupDefTypeInfo;

export const TagGroupDef_TYPE = TagGroupDefTypeInfo.INSTANCE;
