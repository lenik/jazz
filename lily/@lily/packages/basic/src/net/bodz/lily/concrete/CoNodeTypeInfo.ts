
import { EntityPropertyMap, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoNodeValidators from './CoNodeValidators';
import IdEntityTypeInfo from './IdEntityTypeInfo';

export class CoNodeTypeInfo extends IdEntityTypeInfo {

    get name() { return "net.bodz.lily.concrete.CoNode"; }
    get icon() { return "fa-sitemap"; }
    get label() { return "Concrete Node"; }
    get description() { return "A node can have parent, hence multiple nodes forms a tree."; }

    validators = new CoNodeValidators(this);

    declaredProperty: EntityPropertyMap = {
        parent: property({
            type: 'any', icon: "far-tree",
            validator: this.validators.validateParent
        }),
        refCount: property({
            type: 'int', nullable: false, precision: 19, icon: "far-link",
            validator: this.validators.validateRefCount
        }),
        depth: property({
            type: 'int', precision: 19, icon: "far-layer-group",
            validator: this.validators.validateDepth
        }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default CoNodeTypeInfo;