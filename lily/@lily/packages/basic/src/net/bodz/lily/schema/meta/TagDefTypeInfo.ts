import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import TagDefValidators from "./TagDefValidators";
import _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

// Type Info

export class TagDefTypeInfo extends _TagDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.TagDef"
    icon = "fa-tag"
    label = "Tag"

    static validators = new TagDefValidators();

    static declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(TagDefTypeInfo.declaredProperty);
    }

}

export default TagDef;
