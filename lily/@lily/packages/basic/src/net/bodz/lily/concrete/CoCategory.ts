import { int } from '@skeljs/core/src/lang/basetype';
import CoNode from './CoNode';
import CoCategoryTypeInfo from './CoCategoryTypeInfo';

export abstract class CoCategory<This, Id> extends CoNode<This, Id> {
    static readonly TYPE = new CoCategoryTypeInfo();
    
    properties: any
    
    constructor(o: any) {
        super(o);
    }
}

export default CoCategory;