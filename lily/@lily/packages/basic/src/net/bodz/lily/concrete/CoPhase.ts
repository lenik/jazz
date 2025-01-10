import { int } from 'skel01-core/src/lang/basetype';
import CoCode from './CoCode';
import CoPhaseTypeInfo from './CoPhaseTypeInfo';

export abstract class CoPhase<This> extends CoCode<This> {

    static _typeInfo: CoPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

}

export default CoPhase;