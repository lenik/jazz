import CoMomentIntervalValidators from './CoMomentIntervalValidators';
import CoImagedMomentIntervalTypeInfo from './CoImagedMomentIntervalTypeInfo';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';

export class CoImagedMomentIntervalValidators extends CoMomentIntervalValidators {

    constructor(type: CoImagedMomentIntervalTypeInfo) {
        super(type);
    }

    validateProperties(val: any) {
        if (val == null) return;
        this.validateImages(val.images);
    }

    validateImages(val: Attachment[]) {
        if (val == null) return;
        for (let item of val)
            this.validateImage(item);
    }

    validateImage(val: Attachment) {
        if (val.name != null) {
            if (val.name.includes('/'))
                throw new Error("name can't contains slash(/).");
        }
    }

}

export default CoImagedMomentIntervalValidators;