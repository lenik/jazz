<script lang="ts">
import { onMounted, ref } from "vue";

import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";

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
onMounted(() => {
});


</script>

<template>
    <LilyAdmin ref="admin" :type="type" v-model="selection">
        <template #columns>
            <th data-type="integer" data-field="id">Id</th>
            <th data-type="string" data-field="label">Label</th>
            <th data-type="string" data-field="description">Description</th>
            <th data-type="string" data-field="icon">Icon</th>
            <th data-type="integer" data-field="priority">Priority</th>
            <th data-type="integer" data-field="flags">Flags</th>
            <th data-type="string" data-field="state">State</th>
            <th data-type="ZonedDateTime" data-field="creationDate">Creation Date</th>
            <th data-type="ZonedDateTime" data-field="lastModifiedDate">Last Modified Date</th>
            <th data-type="integer" data-field="version">Version</th>
            <th data-type="string" data-field="expr">Expr</th>
            <th data-type="integer" data-field="val">Val</th>
            <th data-type="integer[]" data-field="levels">Levels</th>
            <th data-type="boolean" data-field="descend">Descend</th>
            <th data-type="boolean" data-field="transient_">Transient_</th>
            <th data-type="boolean" data-field="indexed">Indexed</th>
            <th data-type="string" data-field="image">Image</th>
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
