import { int } from 'skel01-core/src/lang/basetype';
import CoNode from './CoNode';
import CoCategoryTypeInfo from './CoCategoryTypeInfo';

export abstract class CoCategory<This, Id> extends CoNode<This, Id> {
    
    static _typeInfo: CoCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    properties: any
    
    constructor(o: any) {
        super(o);
    }
}

export default CoCategory;