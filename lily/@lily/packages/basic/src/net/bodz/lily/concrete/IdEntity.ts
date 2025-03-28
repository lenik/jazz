import type { int } from 'skel01-core/src/lang/basetype';
import CoObject from './CoObject';
import IdEntityTypeInfo from './IdEntityTypeInfo';

export abstract class IdEntity<Id> extends CoObject {

    static _typeInfo: IdEntityTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IdEntityTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    override getClass(): IdEntityTypeInfo {
        return super._getClass() as IdEntityTypeInfo;
    }

    id?: Id

    constructor(o: any) {
        super(o);
    }
}

export default IdEntity;