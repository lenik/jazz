<script lang="ts">
import { onMounted, ref } from "vue";

import { DOUBLE, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import TestApply from "./TestApply";
import TestApplyItem from "./TestApplyItem";
import TestQuestion from "./TestQuestion";

export const title = "Admin view of: Test apply item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import TestApplyItemEditor from "./TestApplyItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = TestApplyItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "TestApply": TestApply.TYPE,
    "TestQuestion": TestQuestion.TYPE,
    "STRING": STRING,
    "DOUBLE": DOUBLE,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="LONG" data-field="id">Id</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="OffsetDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="OffsetDateTime" data-field="lastModified">Last Modified</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="TestApply" data-format="label" data-field="apply">Apply</th>
            <th data-type="TestQuestion" data-format="label" data-field="question">Question</th>
            <th data-type="INT" data-field="answer">Answer</th>
            <th data-type="STRING" data-field="anstext">Anstext</th>
            <th data-type="DOUBLE" data-field="score">Score</th>
            <th data-type="DOUBLE" data-field="waittime">Waittime</th>
        </template>
        <template #preview>
            <TestApplyItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <TestApplyItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
