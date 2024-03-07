import { EntityType, property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import EntityPropertyMap from '@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap';
import StructRowValidators from './StructRowValidators';
import { INT } from '@skeljs/core/src/lang/baseinfo';
import ZonedDateTime from '@skeljs/core/src/lang/time/ZonedDateTime';

export class StructRowTypeInfo extends EntityType {

    get name() { return "net.bodz.lily.concrete.StructRow"; }
    get icon() { return "far-cube"; }
    get label() { return "Content Version"; }
    get description() { return "Content data with version."; }

    validators = new StructRowValidators(this);

    declaredProperty: EntityPropertyMap = {
        creationDate: property({
            type: ZonedDateTime.TYPE, icon: 'far-calendar-plus',
            validator: this.validators.validateCreationDate
        }),
        lastModifiedDate: property({
            type: ZonedDateTime.TYPE, icon: 'far-edit',
            validator: this.validators.validateLastModifiedDate
        }),
        version: property({
            type: INT, icon: 'far-code-branch',
            validator: this.validators.validateVersion
        }),
    };

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default StructRowTypeInfo;