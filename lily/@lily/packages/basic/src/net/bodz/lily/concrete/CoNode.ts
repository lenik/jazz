import { int } from '@skeljs/core/src/lang/basetype';
import IdEntity from './IdEntity';
import CoNodeTypeInfo from './CoNodeTypeInfo';

export abstract class CoNode<This, Id> extends IdEntity<Id> {

    static readonly TYPE = CoNodeTypeInfo.INSTANCE;

    parent?: This
    private _parentId?: int
    get parentId() {
        return this.parent != null ? this.parent.id : this.parentId;
    }
    set parentId(val: int | undefined) {
        this.parentId = val;
    }

    children: This[]
    properties: any

    refCount?: int

    constructor(o: any) {
        super(o);
    }
}

export default CoNode;