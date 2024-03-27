<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";
import User from "@lily/basic/src/net/bodz/lily/schema/account/User";

import ArtifactModel from "../art/ArtifactModel";
import FabProcess from "./FabProcess";
import { FabProcess } from "./FabProcess";
import FabStdProcess from "./FabStdProcess";
import FabTask from "./FabTask";

export const title = "Choose dialog for: Fab process";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const typeMap = {
    "LONG": LONG,
    "User": User.TYPE,
    "Group": Group.TYPE,
    "INT": INT,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabTask": FabTask.TYPE,
    "FabProcess": FabProcess.TYPE,
    "ArtifactModel": ArtifactModel.TYPE,
    "FabStdProcess": FabStdProcess.TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
    "JSON_VARIANT": JSON_VARIANT,
};

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="FabProcess.TYPE" :typeMap="typeMap" :modal="modal">
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
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
