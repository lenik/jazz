<script lang="ts">
import { onMounted, ref } from "vue";

import { BOOLEAN, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";
import { FabTrackTest } from "./FabTrackTest";
import { FabTrack_TYPE } from "./FabTrackTypeInfo";

export const title = "Choose dialog for: Fab track test";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "INT": INT,
    "FabTrack": FabTrack_TYPE,
    "FabStdTest": FabStdTest_TYPE,
    "BOOLEAN": BOOLEAN,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabTrackTest.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabTrack" data-format="label" data-field="track">Track</th>
        <th data-type="FabStdTest" data-format="label" data-field="standard">Standard</th>
        <th data-type="BOOLEAN" data-field="valid">Valid</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
