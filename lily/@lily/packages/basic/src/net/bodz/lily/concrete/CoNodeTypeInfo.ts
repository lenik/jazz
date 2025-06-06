import { property } from 'skel01-dba/src/net/bodz/lily/entity/EntityType';
import CoNodeValidators from './CoNodeValidators';
import IdEntityTypeInfo from './IdEntityTypeInfo';
import TypeInfo from 'skel01-core/src/lang/TypeInfo';
import { INT, UNDEFINED } from 'skel01-core/src/lang/baseinfo';
import { JSON_VARIANT } from 'skel01-core/src/lang/bas-info';


export class CoNodeTypeInfo extends IdEntityTypeInfo {

    readonly validators = new CoNodeValidators(this);

    constructor(idType: TypeInfo<any>) {
        super(idType);
    }

    get name() { return "net.bodz.lily.concrete.CoNode"; }
    get icon() { return "fa-sitemap"; }
    get label() { return "Concrete Node"; }
    get description() { return "A node can have parent, hence multiple nodes forms a tree."; }

    override preamble() {
        super.preamble();
        this.declare({
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
            properties: property({
                type: JSON_VARIANT, nullable: true, icon: "far-bars",
                validator: this.validators.validateProperties
            }),
        });
    }

    static readonly INSTANCE = new CoNodeTypeInfo(UNDEFINED);

}

export default CoNodeTypeInfo;
export const CoNode_TYPE = CoNodeTypeInfo.INSTANCE;
