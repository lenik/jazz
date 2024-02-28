import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import List from "../../../../../java/util/List";
import TagDef from "./TagDef";
import TagGroupDefValidators from "./TagGroupDefValidators";
import _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

export class TagGroupDefTypeInfo extends _TagGroupDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.TagGroupDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Tag Group"; }

    validators = new TagGroupDefValidators(this);

    declaredProperty: EntityPropertyMap = {
        ortho: property({ type: "boolean", nullable: false, validator: this.validators.validateOrtho }),
        tags: property({ type: "List<TagDef>", validator: this.validators.validateTags }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default TagGroupDefTypeInfo;
