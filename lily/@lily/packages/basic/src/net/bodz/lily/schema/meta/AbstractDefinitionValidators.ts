import CoCodeValidators from '../../concrete/CoCodeValidators';
import type AbstractDefinitionTypeInfo from "./AbstractDefinitionTypeInfo";
import SchemaDef from "./SchemaDef";

export class AbstractDefinitionValidators extends CoCodeValidators {

    constructor(type: AbstractDefinitionTypeInfo) {
        super(type);
    }

    get type() {
        return this._type as AbstractDefinitionTypeInfo;
    }

    validateSchema(val: SchemaDef) {
    }

}

export default AbstractDefinitionValidators;
