<template>
  <v-app></v-app>
</template>

<script>
export default {
  name: "MapDisplay",
  data() {
    return {
      map: null,
      markers: [], //나중에 편의시설/아파트 찍을때
    };
  },
  props: {
    houseinfos: Array,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=***API-Key***";
      document.head.appendChild(script);
    }
  },
  watch: {
    houseinfos() {
      this.setMarkers(this.houseinfos);
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map"); //지도를 표시할div
      const options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147), //관심 지역 설정됬으면 그쪽으로 설정해도 될듯..
        level: 8,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    setMarkers(data) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      const positions = data.map(
        (house) => new kakao.maps.LatLng(house.lat, house.lng)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<style></style>
