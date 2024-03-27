<script lang="ts">
import { onMounted, ref } from "vue";

import { BIG_DECIMAL, INT } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import Artifact from "@lily/violet/src/net/bodz/violet/schema/art/Artifact";

import ArtifactModel from "../art/ArtifactModel";
import FabStdProcess from "./FabStdProcess";
import FabStdProcessInput from "./FabStdProcessInput";

export const title = "Admin view of: Fab std process input";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FabStdProcessInputEditor from "./FabStdProcessInputEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabStdProcessInput.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabStdProcess": FabStdProcess.TYPE,
    "ArtifactModel": ArtifactModel.TYPE,
    "Artifact": Artifact.TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="FabStdProcess" data-format="label" data-field="process">Process</th>
            <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
            <th data-type="Artifact" data-format="label" data-field="artifact">Artifact</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
        </template>
        <template #preview>
            <FabStdProcessInputEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabStdProcessInputEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
