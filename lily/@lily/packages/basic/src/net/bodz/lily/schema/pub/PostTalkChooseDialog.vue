<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer, long } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { PostTalk } from "./PostTalk";

export const title = "Choose dialog for: Post talk";
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

const entityChooseDialog = ref<undefined | InstanceType<typeof EntityChooseDialog>>();
defineExpose({ open });

function open(callback?: DialogSelectCallback) {
    entityChooseDialog.value?.open(callback);
}

onMounted(() => {
});

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="PostTalk.TYPE" :modal="modal">
        <th data-type="long" data-field="id">Id</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="string" data-field="state">State</th>
        <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="ZonedDateTime" data-field="beginTime">Begin Time</th>
        <th data-type="ZonedDateTime" data-field="endTime">End Time</th>
        <th data-type="integer" data-field="year">Year</th>
        <th data-type="string" data-field="subject">Subject</th>
        <th data-type="string" data-format="label" data-field="op">Op</th>
        <th data-type="string" data-field="rawText">Raw Text</th>
        <th data-type="string" data-format="label" data-field="form">Form</th>
        <th data-type="string" data-field="formArguments">Form Arguments</th>
        <th data-type="string" data-format="label" data-field="post">Post</th>
        <th data-type="string" data-format="label" data-field="parent">Parent</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
