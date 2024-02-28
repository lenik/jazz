<script lang="ts">
import { onMounted, ref } from "vue";

import { ARRAY, BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import DefaultState from "@skeljs/core/src/net/bodz/bas/repr/state/DefaultState";

import Badge from "./Badge";

export const title = "Admin view of: Badge";
export interface Props {
}

</script>

<script setup lang="ts">
import LilyAdmin from "@skeljs/dba/src/ui/lily/LilyAdmin.vue";

import BadgeEditor from "./BadgeEditor.vue";

const props = withDefaults(defineProps<Props>(), {
});

const admin = ref<InstanceType<typeof LilyAdmin>>();
const type = Badge.TYPE;
const selection = ref<any>({});

const typeMap = {
    "INT": INT,
    "STRING": STRING,
    "DefaultState": DefaultState.TYPE,
    "ZonedDateTime": ZonedDateTime.TYPE,
    "ARRAY(INT)": ARRAY(INT),
    "BOOLEAN": BOOLEAN,
};

onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" :typeMap="typeMap" v-model="selection">
        <template #columns>
            <th data-type="INT" data-field="id">Id</th>
            <th data-type="STRING" data-field="label">Label</th>
            <th data-type="STRING" data-field="description">Description</th>
            <th data-type="STRING" data-field="icon">Icon</th>
            <th data-type="INT" data-field="priority">Priority</th>
            <th data-type="INT" data-field="flags">Flags</th>
            <th data-type="DefaultState" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="INT" data-field="version">Version</th>
            <th data-type="STRING" data-field="expr">Expr</th>
            <th data-type="INT" data-field="val">Val</th>
            <th data-type="ARRAY(INT)" data-field="levels">Levels</th>
            <th data-type="BOOLEAN" data-field="descend">Descend</th>
            <th data-type="BOOLEAN" data-field="transient_">Transient_</th>
            <th data-type="BOOLEAN" data-field="indexed">Indexed</th>
            <th data-type="STRING" data-field="image">Image</th>
        </template>
        <template #preview>
            <BadgeEditor class="editor" v-model="selection" />
        </template>
        <template #side-tools> Side Tools</template>
        <template #editor>
            <BadgeEditor class="editor" v-model="selection" />
        </template>
    </LilyAdmin>
</template>

<style lang="scss"></style>

<style scoped lang="scss">
.lily-admin {
    padding: 0;
}
</style>
