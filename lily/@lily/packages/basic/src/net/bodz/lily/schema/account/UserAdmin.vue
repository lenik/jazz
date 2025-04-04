<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";

import { Person_TYPE } from "../contact/PersonTypeInfo";
import { Group_TYPE } from "./GroupTypeInfo";
import User from "./User";
import { User_TYPE } from "./UserTypeInfo";
import { UserType_TYPE } from "./UserTypeTypeInfo";

export const title = "Admin view of: User";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import UserEditor from "./UserEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = User.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "UserType": UserType_TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Group": Group_TYPE,
    "User": User_TYPE,
    "Person": Person_TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="UserType" data-format="label" data-field="type" title="User type like system-user, normal-user, etc.">Type</th>
            <th data-type="STRING" data-field="name" title="The user name (unique)">Name</th>
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
            <th data-type="Group" data-format="label" data-field="primaryGroup" title="The primary user group, the default value of ownerGroup.">Primary Group</th>
            <th data-type="User" data-format="label" data-field="referer" title="The referer user (used for promotion)">Referer</th>
            <th data-type="Person" data-format="label" data-field="person">Person</th>
        </template>
        <template #preview>
            <UserEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <UserEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
