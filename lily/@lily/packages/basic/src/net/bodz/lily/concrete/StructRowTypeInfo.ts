import { EntityPropertyMap, EntityType, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import StructRowValidators from './StructRowValidators';

export class StructRowTypeInfo extends EntityType {

    get name() { return "net.bodz.lily.concrete.StructRow"; }
    get icon() { return "far-cube"; }
    get label() { return "Content Version"; }
    get description() { return "Content data with version."; }

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