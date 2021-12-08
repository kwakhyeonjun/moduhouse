<template>
  <v-app id="app">
    <v-card>
      <div id="navbar">
        <admin-side-menu></admin-side-menu>
      </div>
      <v-row md="8">
        <v-col md="2" />
        <v-col md="8">
          <v-col md="4" />
          <v-content clipped-left>
            <router-view></router-view>
          </v-content>
        </v-col>
        <v-col md="2" />
      </v-row>
    </v-card>
  </v-app>
</template>

<script>
import AdminSideMenu from "@/components/Member/AdminSideMenu.vue";

export default {
  name: "AdminPage",
  components: {
    AdminSideMenu,
  },
};
</script>

<style>
#navbar {
  float: left;
  position: relative;
}
</style>
