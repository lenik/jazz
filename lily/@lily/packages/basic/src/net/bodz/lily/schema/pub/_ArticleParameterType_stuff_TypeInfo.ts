import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoParameterTypeInfo from "../../concrete/CoParameterTypeInfo";
import _ArticleParameterType_stuff_Validators from "./_ArticleParameterType_stuff_Validators";

export class _ArticleParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "articleparm";

    get name() { return "net.bodz.lily.schema.pub.ArticleParameterType"; }
    get icon() { return "fa-tag"; }

    static FIELD_NAME = "name";
    static FIELD_DUMMY = "dummy";

    static N_NAME = 30;
    static N_DUMMY = 10;

    validators = new _ArticleParameterType_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        name: property({ type: "string", precision: 30, validator: this.validators.validateName }),
        dummy: property({ type: "int", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleParameterType_stuff_TypeInfo;
