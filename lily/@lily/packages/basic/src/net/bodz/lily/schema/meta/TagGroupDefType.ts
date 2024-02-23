import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { List } from "../../../../../java/util/List";
import TagGroupDefValidators from "./TagGroupDefValidators";
import _TagGroupDef_stuff_Type from "./_TagGroupDef_stuff_Type";

// Type Info

export class TagGroupDefType extends _TagGroupDef_stuff_Type {

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
        this.declare(TagGroupDefType.declaredProperty);
    }

}

export default TagGroupDef;
