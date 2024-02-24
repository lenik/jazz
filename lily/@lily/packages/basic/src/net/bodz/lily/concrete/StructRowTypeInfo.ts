import { EntityPropertyMap, EntityType, property } from '@skeljs/dba/src/net/bodz/lily/entity';
import StructRowValidators from './StructRowValidators';

export class StructRowTypeInfo extends EntityType {

    name = "net.bodz.lily.concrete.StructRow"
    icon = "far-cube"
    label = "Content Version"
    description = "Content data with version."

    validators = new StructRowValidators(this);

    declaredProperty: EntityPropertyMap = {
        creationDate: property({
            type: 'date', icon: 'far-calendar-plus',
            validator: this.validators.validateCreationDate
        }),
        lastModifiedDate: property({
            type: 'date', icon: 'far-edit',
            validator: this.validators.validateLastModifiedDate
        }),
        version: property({
            type: 'number', icon: 'far-code-branch',
            validator: this.validators.validateVersion
        }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default StructRowTypeInfo;