<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "lily-basic/src/net/bodz/lily/schema/account/UserTypeInfo";

import PlanTag from "./PlanTag";
import { PlanTag_TYPE } from "./PlanTagTypeInfo";

export const title = "Admin view of: Plan tag";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import PlanTagEditor from "./PlanTagEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = PlanTag.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "PlanTag": PlanTag_TYPE,
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
            <th data-type="STRING" data-field="code">Code</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="PlanTag" data-format="label" data-field="parent">Parent</th>
            <th data-type="INT" data-field="depth">Depth</th>
            <th data-type="INT" data-field="refCount">Ref Count</th>
        </template>
        <template #preview>
            <PlanTagEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <PlanTagEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
