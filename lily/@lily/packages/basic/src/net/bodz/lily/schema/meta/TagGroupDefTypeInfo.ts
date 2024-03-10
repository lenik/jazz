import { BOOLEAN, LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import TagDef from "./TagDef";
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

    override preamble() {
        super.preamble();
        this.declare({
            ortho: property({ type: BOOLEAN, nullable: false, validator: this.validators.validateOrtho }),
            tags: property({ type: LIST(TagDef.TYPE), validator: this.validators.validateTags }),
        });
    }

    static readonly INSTANCE = new TagGroupDefTypeInfo();

}

export default TagGroupDefTypeInfo;
