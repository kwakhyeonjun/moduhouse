<template>
  <v-app>
    <nav-bar></nav-bar>
    <list-subscription></list-subscription>
  </v-app>
</template>

<script>
import NavBar from "@/components/layout/NavBar.vue";
import ListSubscription from "@/components/subscription/ListSubscription.vue";

export default {
  name: "MainSubscription",
  components: {
    NavBar,
    ListSubscription,
  },
};
</script>

<style></style>
