import { LIST } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

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

    override preamble() {
        super.preamble();
        this.declare({
            categories: property({ type: LIST(CategoryDef.TYPE), validator: this.validators.validateCategories }),
            parameters: property({ type: LIST(ParameterDef.TYPE), validator: this.validators.validateParameters }),
            phases: property({ type: LIST(PhaseDef.TYPE), validator: this.validators.validatePhases }),
            priorities: property({ type: LIST(PriorityDef.TYPE), validator: this.validators.validatePriorities }),
            tagGroups: property({ type: LIST(TagGroupDef.TYPE), validator: this.validators.validateTagGroups }),
        });
    }

    constructor() {
        super();
    }

}

export default SchemaDefTypeInfo;
