import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

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
        name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _ArticleParameterType_stuff_TypeInfo;
