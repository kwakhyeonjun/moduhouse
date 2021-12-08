<template>
  <v-app id="qa">
    <nav-bar />
    <router-view />
  </v-app>
</template>

<script>
import NavBar from "../components/layout/NavBar.vue";

export default {
  name: "QA",
  components: { NavBar },
};
</script>

<style>
h2 {
  text-align: center;
  margin: 20px auto;
}
.box {
  display: block;
}
</style>
