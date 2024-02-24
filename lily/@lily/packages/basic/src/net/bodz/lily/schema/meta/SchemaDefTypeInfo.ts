import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CategoryDef from "./CategoryDef";
import ParameterDef from "./ParameterDef";
import PhaseDef from "./PhaseDef";
import PriorityDef from "./PriorityDef";
import SchemaDefValidators from "./SchemaDefValidators";
import TagGroupDef from "./TagGroupDef";
import _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class SchemaDefTypeInfo extends _SchemaDef_stuff_TypeInfo {

    name = "net.bodz.lily.schema.meta.SchemaDef"
    icon = "fa-tag"
    label = "Schema"

    validators = new SchemaDefValidators(this);

    declaredProperty: EntityPropertyMap = {
        categories: property({ type: "CategoryDef[]", validator: this.validators.validateCategories }),
        parameters: property({ type: "ParameterDef[]", validator: this.validators.validateParameters }),
        phases: property({ type: "PhaseDef[]", validator: this.validators.validatePhases }),
        priorities: property({ type: "PriorityDef[]", validator: this.validators.validatePriorities }),
        tagGroups: property({ type: "TagGroupDef[]", validator: this.validators.validateTagGroups }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default SchemaDefTypeInfo;
