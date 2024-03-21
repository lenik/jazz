import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export abstract class CoNode<This, Id> extends IdEntity<Id> {

    static readonly TYPE = CoNodeTypeInfo.INSTANCE;

    parent?: This
    parentId?: Id
    children: This[]
    properties: any

    refCount?: int
    
    constructor(o: any) {
        super(o);
    }
}

export default CoNode;