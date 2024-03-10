import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoParameterTypeInfo from './CoParameterTypeInfo';

export abstract class CoParameter<This> extends CoCode<This> {

    constructor(o: any) {
        super(o);
    }

}

export default CoParameter;