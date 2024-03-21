<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import CoEvent from "./CoEvent";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import DateTime from "@skeljs/core/src/ui/input/DateTime.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

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
    <FieldGroup :type="CoEvent.TYPE" ref="rootElement">
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

<style scoped lang="scss">
</style>
