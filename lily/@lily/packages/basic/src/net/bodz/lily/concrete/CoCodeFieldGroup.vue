<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { CoCode_TYPE } from "./CoCodeTypeInfo";
import CoCode from "./CoCode";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<CoCode<any>>({ required: true });

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
    <FieldGroup :type="CoCode_TYPE" ref="rootElement">
        <FieldRow :property="meta.code" v-model="model.code">
            <input type="text" v-model="model.code" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
