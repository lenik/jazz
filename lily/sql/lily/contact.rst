#\import lily.security

column-property {
    nbank:              bankCount
    nrole:              roleCount
    timezone:           timeZone
    taxid:              taxId
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        partycat
    net.bodz.lily.concrete.CoTag: \
        persontag
    net.bodz.lily.schema.contact.Party: \
        org, \
        orgunit, \
        person
}

table-name {
    org:                net.bodz.lily.schema.contact.Organization
    orgunit:            net.bodz.lily.schema.contact.OrgUnit
    partycat:           net.bodz.lily.schema.contact.PartyCategory
    person:             net.bodz.lily.schema.contact.Person
    personrole:         net.bodz.lily.schema.contact.PersonRole
    persontag:          net.bodz.lily.schema.contact.PersonTagType
    person_tag:         net.bodz.lily.schema.contact.PersonTag
}

table person {
    column alias {
        javaName: contact.rename
    }
    column gender {
        javaType: net.bodz.lily.schema.contact.Gender
    }
    column sexual_orient {
        javaName: sexualOrientation
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table org {
    column alias {
        javaName: contact.rename
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table orgunit {
    column alias {
        javaName: contact.rename
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table personrole {
    column ou {
        javaName: orgUnit
    }
}
