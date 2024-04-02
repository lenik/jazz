import { EntityType, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import StructRowValidators from './StructRowValidators';
import { INT } from '@skeljs/core/src/lang/baseinfo';
import OffsetDateTime from '@skeljs/core/src/lang/time/OffsetDateTime';
import StructRow from './StructRow';

export class StructRowTypeInfo extends EntityType {

    validators = new StructRowValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.concrete.StructRow"; }
    get icon() { return "far-cube"; }
    get label() { return "Content Version"; }
    get description() { return "Content data with version."; }

    override create() {
        return new StructRow();
    }
    
    override preamble() {
        this.declare({
            creationDate: property({
                type: OffsetDateTime.TYPE, icon: 'far-calendar-plus',
                validator: this.validators.validateCreationDate
            }),
            lastModified: property({
                type: OffsetDateTime.TYPE, icon: 'far-edit',
                validator: this.validators.validateLastModified
            }),
            version: property({
                type: INT, icon: 'far-code-branch',
                validator: this.validators.validateVersion
            }),
        });
    }

    static readonly INSTANCE = new StructRowTypeInfo();

}

export default StructRowTypeInfo;
export const StructRow_TYPE = StructRowTypeInfo.INSTANCE;
