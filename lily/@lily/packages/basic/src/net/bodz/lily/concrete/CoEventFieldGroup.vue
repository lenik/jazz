<script lang="ts">
import { onMounted, ref } from "vue";
import type { EntityPropertyMap } from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { CoEvent_TYPE } from "./CoEventTypeInfo";
import CoEvent from "./CoEvent";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<CoEvent<any>>({ required: true });

const props = withDefaults(defineProps<Props>(), {
});

const emit = defineEmits<{
    error: [message: string]
}>();

// property shortcuts

const rootElement = ref<HTMLElement>();

// methods

defineExpose({});

onMounted(() => {
});
</script>

<template>
    <FieldGroup :type="CoEvent_TYPE" ref="rootElement">
        <FieldRow :property="meta.beginTime" v-model="model.beginTime">
            <DateTime v-model="model.beginTime" />
        </FieldRow>
        <FieldRow :property="meta.endTime" v-model="model.endTime">
            <DateTime v-model="model.endTime" />
        </FieldRow>
        <FieldRow :property="meta.year" v-model="model.year">
            <input type="number" v-model="model.year" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
