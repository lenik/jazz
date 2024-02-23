<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { Badge } from "./Badge";

export const title = "Choose dialog for: Badge";
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
    <EntityChooseDialog ref="entityChooseDialog" :type="Badge.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="string" data-field="label">Label</th>
        <th data-type="string" data-field="description">Description</th>
        <th data-type="string" data-field="icon">Icon</th>
        <th data-type="integer" data-field="priority">Priority</th>
        <th data-type="integer" data-field="flags">Flags</th>
        <th data-type="string" data-field="state">State</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="string" data-field="expr">Expr</th>
        <th data-type="integer" data-field="val">Val</th>
        <th data-type="[I" data-field="levels">Levels</th>
        <th data-type="boolean" data-field="descend">Descend</th>
        <th data-type="boolean" data-field="transient_">Transient_</th>
        <th data-type="boolean" data-field="indexed">Indexed</th>
        <th data-type="string" data-field="image">Image</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
