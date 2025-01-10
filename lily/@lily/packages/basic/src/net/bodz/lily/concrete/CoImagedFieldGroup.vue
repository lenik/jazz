<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { CoImaged_TYPE } from "./CoImagedTypeInfo";
import CoImaged from "./CoImaged";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<CoImaged<any>>({ required: true });

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
    <FieldGroup :type="CoImaged_TYPE" ref="rootElement">
        <FieldRow :property="meta.properties" v-model="model.properties">
            <JsonEditor v-model="model.properties" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
