import { int } from 'skel01-core/src/lang/basetype';
import StructRowValidators from 'lily-basic/src/net/bodz/lily/concrete/StructRowValidators';
import IArtifactExtrasTypeInfo from './IArtifactExtrasTypeInfo';

export class IArtifactExtrasValidators extends StructRowValidators {

    constructor(type: IArtifactExtrasTypeInfo) {
        super(type);
    }

    validateColor(val: string) {
    }

    validateCaution(val: string) {
    }

    validateSupplyDelay(val: int) {
    }

}

export default IArtifactExtrasValidators;