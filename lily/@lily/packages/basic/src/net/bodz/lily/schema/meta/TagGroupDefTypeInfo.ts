import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import TagGroupDefValidators from "./TagGroupDefValidators";
import _TagGroupDef_stuff_TypeInfo from "./_TagGroupDef_stuff_TypeInfo";

// Type Info

export class TagGroupDefTypeInfo extends _TagGroupDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.TagGroupDef"
    icon = "fa-tag"
    label = "Tag Group"

    static validators = new TagGroupDefValidators();

    static declaredProperty: EntityPropertyMap = {
        ortho: property({ type: "boolean", nullable: false, validator: this.validators.validateOrtho }),
        tags: property({ type: "List", validator: this.validators.validateTags }),
    }

    constructor() {
        super();
        this.declare(TagGroupDefTypeInfo.declaredProperty);
    }

}

export default TagGroupDef;
