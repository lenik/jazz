import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export abstract class CoNode<This, Id> extends IdEntity<Id> {
    static readonly TYPE = new CoNodeTypeInfo();

    parent?: This
    parentId?: Id

    children: This[]

    refCount?: int
    
    constructor(o: any) {
        super(o);
    }
}

export default CoNode;