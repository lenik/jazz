import { double } from 'skel01-core/src/lang/basetype';
import StructRowValidators from 'lily-basic/src/net/bodz/lily/concrete/StructRowValidators';
import Dim3dTypeInfo from './Dim3dTypeInfo';

export class Dim3dValidators extends StructRowValidators {

    constructor(type: Dim3dTypeInfo) {
        super(type);
    }

    validateDx(val: double) {
    }
    validateDy(val: double) {
    }
    validateDz(val: double) {
    }

}

export default Dim3dValidators;