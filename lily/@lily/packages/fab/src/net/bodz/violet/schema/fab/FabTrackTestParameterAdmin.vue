<script lang="ts">
import { onMounted, ref } from "vue";

import { BOOLEAN, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";

import { FabStdTestParameter_TYPE } from "./FabStdTestParameterTypeInfo";
import { FabStdTester_TYPE } from "./FabStdTesterTypeInfo";
import FabTrackTestParameter from "./FabTrackTestParameter";
import { FabTrackTest_TYPE } from "./FabTrackTestTypeInfo";

export const title = "Admin view of: Fab track test parameter";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FabTrackTestParameterEditor from "./FabTrackTestParameterEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabTrackTestParameter.TYPE;
const selection = ref<any>({});

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

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="FabTrackTest" data-format="label" data-field="test">Test</th>
            <th data-type="FabStdTestParameter" data-format="label" data-field="parameter">Parameter</th>
            <th data-type="FabStdTester" data-format="label" data-field="tester">Tester</th>
            <th data-type="STRING" data-field="actual">Actual</th>
            <th data-type="BOOLEAN" data-field="valid">Valid</th>
        </template>
        <template #preview>
            <FabTrackTestParameterEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabTrackTestParameterEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
