import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoParameterTypeInfo from './CoParameterTypeInfo';

export abstract class CoParameter<This> extends CoCode<This> {

    static readonly TYPE = CoParameterTypeInfo.INSTANCE;

    constructor(o: any) {
        super(o);
    }

}

export default CoParameter;