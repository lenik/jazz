<script lang="ts">

import { onMounted } from "vue";

import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import EntityChooseDialog from "@skeljs/dba/src/ui/lily/EntityChooseDialog.vue";

export interface Props {
    modal?: boolean | string
}
</script>

<script setup lang="ts">
const model = defineModel();

const props = withDefaults(defineProps<Props>(), {
    modal: true
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

%s(() => {
onMounted
});
</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Person.TYPE" :modal="modal">
        <th data-type="long" data-field="id">Id</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="net.bodz.bas.repr.state.State" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="Moment" data-field="beginTime">Begin Time</th>
        <th data-type="Moment" data-field="endTime">End Time</th>
        <th data-type="integer" data-field="year">Year</th>
        <th data-type="string" data-field="subject">Subject</th>
        <th data-type="string" data-format="label" data-field="op">Op</th>
        <th data-type="string" data-field="rawText">Raw Text</th>
        <th data-type="string" data-format="label" data-field="form">Form</th>
        <th data-type="string" data-field="formArguments">Form Arguments</th>
        <th data-type="string" data-format="label" data-field="article">Article</th>
        <th data-type="string" data-format="label" data-field="parent">Parent</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
