<template>
  <v-app>
    <router-view
      @houseUpdate="sendAddress"
      @houseDetail="sendDetail"
      :houseinfo="this.houseinfo"
    ></router-view>
  </v-app>
</template>

<script>
export default {
  name: "MapSearch",
  data() {
    return {
      houseinfos: [],
      houseinfo: null,
    };
  },
  methods: {
    sendAddress(data) {
      this.houseinfos = data;
      this.$emit("houseUpdate", this.houseinfos);
    },
    sendDetail(data) {
      this.houseinfo = data;
    },
  },
};
</script>

<style>
.v-application--wrap {
  padding: 2px;
  max-width: 98%;
}
</style>
