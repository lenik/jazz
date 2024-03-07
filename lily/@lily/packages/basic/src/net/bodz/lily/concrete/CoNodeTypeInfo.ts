
import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoNodeValidators from './CoNodeValidators';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import TypeInfo from '@skeljs/core/src/lang/TypeInfo';
import { INT } from '@skeljs/core/src/lang/baseinfo';

export class CoNodeTypeInfo extends IdEntityTypeInfo {

    selfType: TypeInfo<any>;

    get name() { return "net.bodz.lily.concrete.CoNode"; }
    get icon() { return "fa-sitemap"; }
    get label() { return "Concrete Node"; }
    get description() { return "A node can have parent, hence multiple nodes forms a tree."; }

    validators = new CoNodeValidators(this);

    declaredProperty: EntityPropertyMap = {
        parent: property({
            type: this, icon: "far-tree",
            validator: this.validators.validateParent
        }),
        refCount: property({
            type: INT, nullable: false, precision: 19, icon: "far-link",
            validator: this.validators.validateRefCount
        }),
        depth: property({
            type: INT, precision: 19, icon: "far-layer-group",
            validator: this.validators.validateDepth
        }),
    };

    constructor(selfType: TypeInfo<any>, idType: TypeInfo<any>) {
        super(idType);
        this.selfType = selfType;
        this.declaredProperty.parent.type = selfType;
        this.declare(this.declaredProperty);
    }

}

export default CoNodeTypeInfo;