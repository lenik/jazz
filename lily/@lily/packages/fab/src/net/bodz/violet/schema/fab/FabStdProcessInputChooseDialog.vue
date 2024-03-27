<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import type { DialogSelectCallback } from "@skeljs/core/src/ui/types";
import Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import ArtifactModel from "../art/ArtifactModel";
import FabStdProcess from "./FabStdProcess";
import { FabStdProcessInput } from "./FabStdProcessInput";

export const title = "Choose dialog for: Fab std process input";
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
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabStdProcess": FabStdProcess.TYPE,
    "ArtifactModel": ArtifactModel.TYPE,
    "Artifact": Artifact.TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
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
    <EntityChooseDialog ref="entityChooseDialog" :type="FabStdProcessInput.TYPE" :typeMap="typeMap" :modal="modal">
        <th data-type="INT" data-field="id">Id</th>
        <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
        <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
        <th data-type="INT" data-field="version">Version</th>
        <th data-type="FabStdProcess" data-format="label" data-field="process">Process</th>
        <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
        <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
        <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
    </EntityChooseDialog>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
