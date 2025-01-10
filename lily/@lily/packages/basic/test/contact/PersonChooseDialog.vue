<script lang="ts">
import { onMounted, ref } from "vue";
import { DialogSelectCallback } from "skel01-core/src/ui/types";

import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import LocalDate from "skel01-core/src/lang/time/LocalDate";

import { Person_TYPE } from './PersonTypeInfo';
</script>

<script setup lang="ts">
import EntityChooseDialog from 'skel01-dba/src/ui/lily/EntityChooseDialog.vue';

const model = defineModel();

interface Props {
    modal?: boolean | string
}

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

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "LocalDate": LocalDate.TYPE,
    "JSON_VARIANT": JSON_VARIANT,
    "Person": Person_TYPE,
};

</script>

<template>
    <EntityChooseDialog ref="entityChooseDialog" :type="Person_TYPE" :modal="modal">
        <th data-field="id" data-type="INT" class="id">ID</th>
        <th data-field="label" data-type="STRING">Name</th>
        <th data-field="description" data-type="STRING">Description</th>
        <th data-field="gender" data-type="Gender">Gender</th>
        <th data-field="birthday" data-type="LocalDate">Birthday</th>
        <th data-field="father.label" data-type="Person">Father</th>
        <th data-field="mother.label" data-type="Person">Mother</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
