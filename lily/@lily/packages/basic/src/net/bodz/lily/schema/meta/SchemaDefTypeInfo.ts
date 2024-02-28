import { EntityPropertyMap, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import List from "../../../../../java/util/List";
import CategoryDef from "./CategoryDef";
import ParameterDef from "./ParameterDef";
import PhaseDef from "./PhaseDef";
import PriorityDef from "./PriorityDef";
import SchemaDefValidators from "./SchemaDefValidators";
import TagGroupDef from "./TagGroupDef";
import _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class SchemaDefTypeInfo extends _SchemaDef_stuff_TypeInfo {

    get name() { return "net.bodz.lily.schema.meta.SchemaDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Schema"; }

    validators = new SchemaDefValidators(this);

    declaredProperty: EntityPropertyMap = {
        categories: property({ type: "List<CategoryDef>", validator: this.validators.validateCategories }),
        parameters: property({ type: "List<ParameterDef>", validator: this.validators.validateParameters }),
        phases: property({ type: "List<PhaseDef>", validator: this.validators.validatePhases }),
        priorities: property({ type: "List<PriorityDef>", validator: this.validators.validatePriorities }),
        tagGroups: property({ type: "List<TagGroupDef>", validator: this.validators.validateTagGroups }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default SchemaDefTypeInfo;
