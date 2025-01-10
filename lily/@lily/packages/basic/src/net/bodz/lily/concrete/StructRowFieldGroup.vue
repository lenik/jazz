<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { StructRow_TYPE } from "./StructRowTypeInfo";
import StructRow from "./StructRow";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import DateTime from "skel01-core/src/ui/input/DateTime.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<StructRow>({ required: true });

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
    <FieldGroup :type="StructRow_TYPE" ref="rootElement">
        <FieldRow :property="meta.creationDate" v-model="model.creationDate">
            <DateTime disabled v-model="model.creationDate" />
        </FieldRow>
        <FieldRow :property="meta.lastModified" v-model="model.lastModified">
            <DateTime disabled v-model="model.lastModified" />
        </FieldRow>
        <FieldRow :property="meta.version" v-model="model.version">
            <input disabled type="number" v-model="model.version" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss">
.component-root {
    padding: 0;
}
</style>
