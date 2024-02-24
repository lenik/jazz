import { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoTagTypeInfo from "../../concrete/CoTagTypeInfo";
import _PersonTagType_stuff_Validators from "./_PersonTagType_stuff_Validators";

export class _PersonTagType_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "persontag";

    name = "net.bodz.lily.schema.contact.PersonTagType"
    icon = "fa-tag"

    validators = new _PersonTagType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PersonTagType_stuff_TypeInfo;
