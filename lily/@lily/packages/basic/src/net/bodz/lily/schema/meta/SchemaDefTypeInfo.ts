import { LIST } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import { CategoryDef_TYPE } from "./CategoryDefTypeInfo";
import { ParameterDef_TYPE } from "./ParameterDefTypeInfo";
import { PhaseDef_TYPE } from "./PhaseDefTypeInfo";
import { PriorityDef_TYPE } from "./PriorityDefTypeInfo";
import SchemaDef from "./SchemaDef";
import SchemaDefValidators from "./SchemaDefValidators";
import { TagGroupDef_TYPE } from "./TagGroupDefTypeInfo";
import _SchemaDef_stuff_TypeInfo from "./_SchemaDef_stuff_TypeInfo";

export class SchemaDefTypeInfo extends _SchemaDef_stuff_TypeInfo {

    readonly validators = new SchemaDefValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.SchemaDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Schema"; }

    override create() {
        return new SchemaDef();
    }

    override preamble() {
        super.preamble();
        this.declare({
            categories: property({ type: LIST(CategoryDef_TYPE), validator: this.validators.validateCategories }),
            parameters: property({ type: LIST(ParameterDef_TYPE), validator: this.validators.validateParameters }),
            phases: property({ type: LIST(PhaseDef_TYPE), validator: this.validators.validatePhases }),
            priorities: property({ type: LIST(PriorityDef_TYPE), validator: this.validators.validatePriorities }),
            tagGroups: property({ type: LIST(TagGroupDef_TYPE), validator: this.validators.validateTagGroups }),
        });
    }

    static readonly INSTANCE = new SchemaDefTypeInfo();

}

export default SchemaDefTypeInfo;

export const SchemaDef_TYPE = SchemaDefTypeInfo.INSTANCE;
