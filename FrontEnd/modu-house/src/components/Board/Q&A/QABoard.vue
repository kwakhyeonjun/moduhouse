<template>
  <v-app>
    <v-list>
      <v-list-item-title><h2>문의하기</h2></v-list-item-title>
      <v-divider></v-divider>
      <v-row>
        <v-col cols="2" />
        <v-col cols="8">
          <v-container>
            <AskForm />
          </v-container>
        </v-col>
        <v-col cols="2" />
      </v-row>
    </v-list>
  </v-app>
</template>

<script>
import AskForm from "@/components/Board/Q&A/child/AskForm.vue";
export default {
  name: "QABoard",
  components: {
    AskForm,
  },
};
</script>

<style></style>
