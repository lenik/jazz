import { INT, BOOLEAN, STRING, LIST } from '@skeljs/core/src/lang/baseinfo';
import { JSON_VARIANT } from '@skeljs/core/src/lang/bas-info';
import ZonedDateTime from '@skeljs/core/src/lang/time/ZonedDateTime';
import Attachment from '@skeljs/core/src/net/bodz/lily/entity/Attachment';
import { property } from '@skeljs/dba/src/net/bodz/lily/entity/EntityType';
import CoImagedTypeInfo from '../../src/net/bodz/lily/concrete/CoImagedTypeInfo';
import Contact from './Contact';

export class PartyTypeInfo extends CoImagedTypeInfo {

    override preamble() {
        super.preamble();
        this.declare({
            category: property({ type: JSON_VARIANT }),

            birthday: property({ type: ZonedDateTime.TYPE, icon: "fab-pagelines" }),

            locale: property({ type: STRING }),
            timeZoneId: property({ type: STRING }),

            peer: property({ type: BOOLEAN, nullable: false }),
            customer: property({ type: BOOLEAN, nullable: false }),
            supplier: property({ type: BOOLEAN, nullable: false }),

            tags: property({ type: LIST(STRING) }),

            subject: property({ type: STRING }),
            contacts: property({ type: LIST(Contact.TYPE) }),

            bank: property({ type: STRING }),
            account: property({ type: STRING }),
        });
    }

    constructor() {
        super(INT);
    }
}

export default PartyTypeInfo;
