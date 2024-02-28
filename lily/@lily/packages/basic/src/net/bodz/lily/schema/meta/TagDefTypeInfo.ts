import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";

import TagDefValidators from "./TagDefValidators";
import _TagDef_stuff_TypeInfo from "./_TagDef_stuff_TypeInfo";

export class TagDefTypeInfo extends _TagDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.TagDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Tag"; }

    validators = new TagDefValidators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default TagDefTypeInfo;
