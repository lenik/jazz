import { BOOLEAN, LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

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
    get label() { return "Tag Group"; }

    override create() {
        return new TagGroupDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
            ortho: property({ type: BOOLEAN, nullable: false, validator: this.validators.validateOrtho }),
            tags: property({ type: LIST(TagDef_TYPE), validator: this.validators.validateTags }),
        });
    }

    static readonly INSTANCE = new TagGroupDefTypeInfo();

}

export default TagGroupDefTypeInfo;

export const TagGroupDef_TYPE = TagGroupDefTypeInfo.INSTANCE;
