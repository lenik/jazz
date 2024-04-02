<script lang="ts">
import { onMounted, ref } from "vue";

import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";
import { ArtifactModel_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import FabTaskItem from "./FabTaskItem";
import { FabTask_TYPE } from "./FabTaskTypeInfo";

export const title = "Admin view of: Fab task item";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import FabTaskItemEditor from "./FabTaskItemEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = FabTaskItem.TYPE;
const selection = ref<any>({});

const typeMap = {
    "LONG": LONG,
    "INT": INT,
    "DefaultState": DefaultState.TYPE,
    "OffsetDateTime": OffsetDateTime.TYPE,
    "FabTask": FabTask_TYPE,
    "STRING": STRING,
    "ArtifactModel": ArtifactModel_TYPE,
    "BIG_DECIMAL": BIG_DECIMAL,
    "JSON_VARIANT": JSON_VARIANT,
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
            <th data-type="OffsetDateTime" data-field="beginTime">Begin Time</th>
            <th data-type="OffsetDateTime" data-field="endTime">End Time</th>
            <th data-type="INT" data-field="year">Year</th>
            <th data-type="FabTask" data-format="label" data-field="task">Task</th>
            <th data-type="OffsetDateTime" data-field="deadline">Deadline</th>
            <th data-type="STRING" data-field="status">Status</th>
            <th data-type="ArtifactModel" data-format="label" data-field="model">Model</th>
            <th data-type="BIG_DECIMAL" data-field="quantity">Quantity</th>
            <th data-type="JSON_VARIANT" data-field="batch">Batch</th>
            <th data-type="INT" data-field="trackCount">Track Count</th>
        </template>
        <template #preview>
            <FabTaskItemEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <FabTaskItemEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
