column-property {
    creation:   creationDate
    lastmod:    lastModifiedDate
    props:      properties
    nobj:       -
    uid:        ownerUserId
    gid:        ownerGroupId
    mode:       accessMode
    t0:         beginTime
    t1:         endTime
    code:       codeName
    imagealt:   imageAlt
    nref:       -
    formargs:   -
    text:       rawText
}

key-columns {
    format uid {
        pattern: ^uid$
        alias: ownerUser
        component: id
    }
    format gid {
        pattern: ^gid$
        alias: ownerGroup
        component: id
    }
    format _id {
        pattern: ^(.*)_(id)$
        alias: $1
        component: $2
    }
}

table-name {
    _cat:       net.bodz.lily.schema.CategoryDef
    _form:      net.bodz.lily.schema.FormDef
    _parm:      net.bodz.lily.schema.ParameterDef
    _phase:     net.bodz.lily.schema.PhaseDef
    _priority:  net.bodz.lily.schema.PriorityDef
    _schema:    net.bodz.lily.schema.SchemaDef
    _tag:       net.bodz.lily.schema.TagDef
    _tagv:      net.bodz.lily.schema.TagGroupDef
    
    user:       net.bodz.lily.security.User
    usercat:    net.bodz.lily.security.UserCategory
    useroid:    net.bodz.lily.security.UserOtherId
    usersec:    net.bodz.lily.security.UserSecret
    user_run:   net.bodz.lily.security.UserRun
    
    group:      net.bodz.lily.security.Group
    role:       net.bodz.lily.security.Role
    policy:     net.bodz.lily.security.Policy
    org:        net.bodz.lily.contact.Organization
    orgunit:    net.bodz.lily.contact.OrgUnit
    person:     net.bodz.lily.contact.Person
    
    zone:       net.bodz.lily.geo.Zone
}

column-level {
    label: 1
    description: 1
}

join-level {
    parent: 1
}
