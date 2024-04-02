<script lang="ts">
import { onMounted, ref } from "vue";

import { BOOLEAN, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { FabStdTestParameter_TYPE } from "./FabStdTestParameterTypeInfo";
import { FabStdTester_TYPE } from "./FabStdTesterTypeInfo";
import { FabTrackTestParameter } from "./FabTrackTestParameter";
import { FabTrackTest_TYPE } from "./FabTrackTestTypeInfo";

export const title = "Choose dialog for: Fab track test parameter";
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
    "FabTrackTest": FabTrackTest_TYPE,
    "FabStdTestParameter": FabStdTestParameter_TYPE,
    "FabStdTester": FabStdTester_TYPE,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabTrackTestParameter.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="LONG" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabTrackTest" data-format="label" data-field="test">Test</th>
        <th data-type="FabStdTestParameter" data-format="label" data-field="parameter">Parameter</th>
        <th data-type="FabStdTester" data-format="label" data-field="tester">Tester</th>
        <th data-type="STRING" data-field="actual">Actual</th>
        <th data-type="BOOLEAN" data-field="valid">Valid</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
