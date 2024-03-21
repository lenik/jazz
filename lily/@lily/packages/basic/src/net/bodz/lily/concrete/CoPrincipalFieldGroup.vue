<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import CoPrincipal from "./CoPrincipal";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "@skeljs/core/src/ui/FieldRow.vue";
import FieldGroup from "@skeljs/dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<CoPrincipal>({ required: true });

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
    <FieldGroup :type="CoPrincipal.TYPE" ref="rootElement">
        <FieldRow :property="meta.name" v-model="model.name">
            <input type="text" v-model="model.name" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
