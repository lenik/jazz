<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import DefaultState from "skel01-core/src/net/bodz/bas/repr/state/DefaultState";
import type { DialogSelectCallback } from "skel01-core/src/ui/types";
import { ArtifactModel_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import { FabTaskItem } from "./FabTaskItem";
import { FabTask_TYPE } from "./FabTaskTypeInfo";

export const title = "Choose dialog for: Fab task item";
export interface Props {
    modal?: boolean | string
}

</script>

<script setup lang="ts">
import EntityChooseDialog from "skel01-dba/src/ui/lily/EntityChooseDialog.vue";

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
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabTask": FabTask_TYPE,
    "STRING": STRING,
    "ArtifactModel": ArtifactModel_TYPE,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabTaskItem.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
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
        <th data-type="OffsetDateTime" data-field="deadline">Deadline</th>
        <th data-type="STRING" data-field="status">Status</th>
        <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
        <th data-type="INT" data-field="trackCount">Track Count</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
