import { int, long } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoRelationTypeInfo from './CoRelationTypeInfo';

export abstract class CoRelation extends IdEntity<long> {

    static _typeInfo: CoRelationTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoRelationTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }

}

export default CoRelation;