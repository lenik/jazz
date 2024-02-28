<script lang="ts">
import { onMounted, ref } from "vue";

import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";

import FormDef from "./FormDef";
import { FormParameter } from "./FormParameter";

export const title = "Choose dialog for: Form parameter";
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
    "INT": INT,
    "FormDef": FormDef.TYPE,
    "STRING": STRING,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FormParameter.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="FormDef" data-format="label" data-field="form">Form</th>
        <th data-type="STRING" data-field="name">Name</th>
        <th data-type="STRING" data-field="value">Value</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
