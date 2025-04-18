<script lang="ts">
import { onMounted, ref } from "vue";
import EntityPropertyMap from "skel01-dba/src/net/bodz/lily/entity/EntityPropertyMap";
import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import { CoImagedEvent_TYPE } from "./CoImagedEventTypeInfo";
import CoImagedEvent from "./CoImagedEvent";

export interface Props {
    meta: EntityPropertyMap
}
</script>

<script setup lang="ts">
import FieldRow from "skel01-core/src/ui/FieldRow.vue";
import JsonEditor from "skel01-core/src/ui/input/JsonEditor.vue";
import FieldGroup from "skel01-dba/src/ui/lily/FieldGroup.vue";

const model = defineModel<CoImagedEvent<any>>({ required: true });

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
    <FieldGroup :type="CoImagedEvent_TYPE" ref="rootElement">
        <FieldRow :property="meta.properties" v-model="model.properties">
            <JsonEditor v-model="model.properties" />
        </FieldRow>
        <FieldRow :property="meta.images" v-model="model.images">
            <AttachmentsEditor :className="model.getClass().name" 
                :id="model.id" v-model="model.images" />
        </FieldRow>
        <FieldRow :property="meta.videos" v-model="model.videos">
            <AttachmentsEditor :className="model.getClass().name" 
                :id="model.id" v-model="model.videos" />
        </FieldRow>
    </FieldGroup>
</template>

<style scoped lang="scss"></style>
