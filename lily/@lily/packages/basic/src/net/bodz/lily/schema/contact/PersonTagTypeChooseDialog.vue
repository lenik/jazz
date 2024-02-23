<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { PersonTagType } from "./PersonTagType";

export const title = "Choose dialog for: Person tag type";
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PersonTagType.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="string" data-format="label" data-field="ownerUser">Owner User</th>
        <th data-type="string" data-format="label" data-field="ownerGroup">Owner Group</th>
        <th data-type="integer" data-field="accessMode">Access Mode</th>
        <th data-type="integer" data-field="acl">Acl</th>
        <th data-type="string" data-field="code">Code</th>
        <th data-type="string" data-field="label">Label</th>
        <th data-type="string" data-field="description">Description</th>
        <th data-type="string" data-field="icon">Icon</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="string" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="string" data-format="label" data-field="parent">Parent</th>
        <th data-type="integer" data-field="depth">Depth</th>
        <th data-type="integer" data-field="refCount">Ref Count</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
