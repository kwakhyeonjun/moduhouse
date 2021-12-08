<template>
  <div class="map-display">
    <div id="navbar">
      <map-nav-bar />
    </div>
    <div id="contents">
      <map-search v-on:houseUpdate="sendAddress" />
    </div>
    <div id="map">
      <map-display :houseinfos="this.houseinfos" />
    </div>
  </div>
</template>

<script>
import MapDisplay from "@/components/Map/MapDisplay.vue";
import MapNavBar from "@/components/Map/MapNavBar.vue";
import MapSearch from "@/components/Map/MapSearch.vue";

export default {
  name: "Map",
  data() {
    return {
      houseinfos: [],
    };
  },
  components: {
    MapDisplay,
    MapNavBar,
    MapSearch,
  },
  methods: {
    sendAddress(data) {
      this.houseinfos = data;
    },
  },
};
</script>

<style>
.left-nav {
  padding: 0;
  margin: 0;
  width: 5%;
  float: left;
  box-shadow: 1px 1px 4px;
}

.map-display {
  z-index: 7;
}
#contents {
  padding: 0;
  margin: 0;
  width: 315px;
  float: left;
}

.fullscreen {
  width: 100%;
  height: 100vh;
}

#tool {
  height: 100vh;
}

#navbar {
  float: left;
  position: relative;
}

#map {
  height: 100vh;
}
</style>
