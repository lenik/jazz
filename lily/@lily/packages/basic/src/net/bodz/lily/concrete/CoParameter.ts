import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoParameterTypeInfo from './CoParameterTypeInfo';

export abstract class CoParameter<This> extends CoCode<This> {

    static _typeInfo: CoParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

}

export default CoParameter;