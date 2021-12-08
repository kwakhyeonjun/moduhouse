function buildMap() {
  const script = document.createElement("script");
  /* global kakao */
  script.onload = () => kakao.maps.load(this.initMap);
  script.src =
    "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e26ecbc3d991acc6e8a52c7fa163a5ca";
  document.head.appendChild(script);
}