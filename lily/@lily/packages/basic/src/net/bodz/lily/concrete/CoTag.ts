import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoTagTypeInfo from './CoTagTypeInfo';

export abstract class CoTag<This> extends CoCode<This> {

    static _typeInfo: CoTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

}

export default CoTag;