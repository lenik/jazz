import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoPhaseTypeInfo from './CoPhaseTypeInfo';

export abstract class CoPhase<This> extends CoCode<This> {

    constructor(o: any) {
        super(o);
    }

}

export default CoPhase;