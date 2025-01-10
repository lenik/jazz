<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import { Group_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/GroupTypeInfo";
import { User_TYPE } from "@lily/basic/src/net/bodz/lily/schema/account/UserTypeInfo";
import { ArtifactModel_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import FabProcess from "./FabProcess";
import { FabProcess_TYPE } from "./FabProcessTypeInfo";
import { FabStdProcess_TYPE } from "./FabStdProcessTypeInfo";
import { FabTask_TYPE } from "./FabTaskTypeInfo";

export const title = "Admin view of: Fab process";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "skel01-dba/src/ui/lily/LilyAdmin.vue";

import FabProcessEditor from "./FabProcessEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabProcess.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "User": User_TYPE,
    "Group": Group_TYPE,
    "INT": INT,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabTask": FabTask_TYPE,
    "FabProcess": FabProcess_TYPE,
    "ArtifactModel": ArtifactModel_TYPE,
    "FabStdProcess": FabStdProcess_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
    "JSON_VARIANT": JSON_VARIANT,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
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
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="FabTask" data-format="label" data-field="task">Task</th>
            <th data-type="FabProcess" data-format="label" data-field="parent">Parent</th>
            <th data-type="ArtifactModel" data-format="label" data-field="output">Output</th>
            <th data-type="FabStdProcess" data-format="label" data-field="standard">Standard</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="OffsetDateTime" data-field="since">Since</th>
            <th data-type="OffsetDateTime" data-field="deadline">Deadline</th>
            <th data-type="INT" data-field="trackCount">Track Count</th>
        </template>
        <template #preview>
            <FabProcessEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabProcessEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
