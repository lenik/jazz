import { int } from 'skel01-core/src/lang/basetype';
import CoObject from './CoObject';
import CoEntityTypeInfo from './CoEntityTypeInfo';

export abstract class CoEntity<Id> extends CoObject {

    static _typeInfo: CoEntityTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoEntityTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default CoEntity;