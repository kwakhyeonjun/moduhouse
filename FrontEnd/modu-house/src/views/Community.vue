<template>
  <v-app id="community">
    <nav-bar />
    <router-view />
    <footer-bar></footer-bar>
  </v-app>
</template>

<script>
import NavBar from "../components/layout/NavBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";

export default {
  components: { NavBar, FooterBar },
};
</script>

<style></style>
