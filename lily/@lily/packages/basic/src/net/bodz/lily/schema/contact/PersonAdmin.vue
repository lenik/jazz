<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BOOLEAN, CHAR, INT, STRING } from "skel01-core/src/lang/baseinfo";
import LocalDate from "skel01-core/src/lang/time/LocalDate";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";

import { Group_TYPE } from "../account/GroupTypeInfo";
import { User_TYPE } from "../account/UserTypeInfo";
import { PartyCategory_TYPE } from "./PartyCategoryTypeInfo";
import Person from "./Person";
import { Person_TYPE } from "./PersonTypeInfo";

export const title = "Admin view of: Person";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import PersonEditor from "./PersonEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Person.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "PartyCategory": PartyCategory_TYPE,
    "LocalDate": LocalDate.TYPE,
    "Person": Person_TYPE,
    "BOOLEAN": BOOLEAN,
    "CHAR": CHAR,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th class="hidden" data-type="JSON_VARIANT" data-field="files">Files</th>
            <th data-type="PartyCategory" data-format="label" data-field="category">Category</th>
            <th data-type="LocalDate" data-field="birthday">Birthday</th>
            <th data-type="Person" data-format="label" data-field="father">Father</th>
            <th data-type="Person" data-format="label" data-field="mother">Mother</th>
            <th data-type="STRING" data-field="langTag">Lang Tag</th>
            <th data-type="STRING" data-field="timeZone">Time Zone</th>
            <th data-type="INT" data-field="roleCount">Role Count</th>
            <th data-type="BOOLEAN" data-field="customer">Customer</th>
            <th data-type="BOOLEAN" data-field="supplier">Supplier</th>
            <th data-type="BOOLEAN" data-field="employee">Employee</th>
            <th data-type="STRING" data-field="subject">Subject</th>
            <th data-type="INT" data-field="bankCount">Bank Count</th>
            <th data-type="CHAR" data-field="gender">Gender</th>
            <th data-type="STRING" data-field="pronoun">Pronoun</th>
            <th data-type="STRING" data-field="sexualOrientation">Sexual Orientation</th>
            <th data-type="STRING" data-field="homeland">Homeland</th>
            <th data-type="STRING" data-field="passport">Passport</th>
            <th data-type="STRING" data-field="ssn">Ssn</th>
            <th data-type="STRING" data-field="dln">Dln</th>
        </template>
        <template #preview>
            <PersonEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PersonEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
