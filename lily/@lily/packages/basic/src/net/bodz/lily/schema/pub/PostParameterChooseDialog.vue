<script lang="ts">
import { Moment } from "moment";
import { onMounted, ref } from "vue";

import type { double, integer } from "@skeljs/core/src/lang/type";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import { PostParameter } from "./PostParameter";

export const title = "Choose dialog for: Post parameter";
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
    <EntityChooseDialog ref="entityChooseDialog" :type="PostParameter.TYPE" :modal="modal">
        <th data-type="integer" data-field="id">Id</th>
        <th data-type="Moment" data-field="creationDate">Creation Date</th>
        <th data-type="Moment" data-field="lastModifiedDate">Last Modified Date</th>
        <th data-type="integer" data-field="version">Version</th>
        <th data-type="string" data-format="label" data-field="post">Post</th>
        <th data-type="string" data-format="label" data-field="parameter">Parameter</th>
        <th data-type="integer" data-field="ival">Ival</th>
        <th data-type="double" data-field="fval">Fval</th>
        <th data-type="string" data-field="sval">Sval</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
