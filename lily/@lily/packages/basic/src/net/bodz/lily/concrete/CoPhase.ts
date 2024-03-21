import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoPhaseTypeInfo from './CoPhaseTypeInfo';

export abstract class CoPhase<This> extends CoCode<This> {

    static readonly TYPE = CoPhaseTypeInfo.INSTANCE;

    constructor(o: any) {
        super(o);
    }

}

export default CoPhase;