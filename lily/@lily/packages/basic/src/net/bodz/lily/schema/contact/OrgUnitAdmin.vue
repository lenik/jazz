<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";

import Object from "../../../../../java/lang/Object";
import Group from "../account/Group";
import User from "../account/User";
import OrgUnit from "./OrgUnit";
import Organization from "./Organization";

export const title = "Admin view of: Org unit";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import OrgUnitEditor from "./OrgUnitEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = OrgUnit.TYPE;
const selection = ref<any>({});

const typeMap = {
    "Object": Object.TYPE,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "STRING": STRING,
    "int": int.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Organization": Organization.TYPE,
    "OrgUnit": OrgUnit.TYPE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="Object" data-field="id">Id</th>
            <th data-type="User" data-format="label" data-field="ownerUser">Owner User</th>
            <th data-type="Group" data-format="label" data-field="ownerGroup">Owner Group</th>
            <th data-type="INT" data-field="accessMode">Access Mode</th>
            <th data-type="INT" data-field="acl">Acl</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="int" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="JSON_VARIANT" data-field="properties">Properties</th>
            <th data-type="Organization" data-format="label" data-field="org">Org</th>
            <th data-type="OrgUnit" data-format="label" data-field="parent">Parent</th>
            <th data-type="INT" data-field="depth">Depth</th>
        </template>
        <template #preview>
            <OrgUnitEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <OrgUnitEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
