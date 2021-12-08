<template>
  <v-carousel
    cycle
    height="125"
    hide-delimiter-background
    delimiter-icon="mdi-minus"
    show-arrows-on-hover
  >
    <!-- <v-carousel-item v-for="(slide, i) in slides" :key="i"> -->
    <!-- <v-img :src=require('../assets/싸피사진2')></v-img> -->
    <!-- <v-sheet :color="colors[i]" height="100%">
        <v-row class="fill-height" align="center" justify="center">
          <div class="text-h2">{{ slide }} Slide</div>
        </v-row>
      </v-sheet> -->
    <v-carousel-item v-for="(item, i) in items" :key="i" :src="item.src" eager>
      <v-img :src="item.src" height="100%" eager @click="moveLink(item.link)" />
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          src: "https://www.ssafy.com/gate/service/swpcommboard/swpnotice/swpcommboardServlet_file_20211018105714869_1_.jpg",
          link: "https://www.ssafy.com/ksp/servlet/swp.board.controller.SwpBoardServlet?p_process=select-board-view&p_menu_cd=M0202&p_tabseq=226507&p_seq=81&p_pageno=",
        },
        {
          src: "https://www.ssafy.com/gate/service/swpcommboard/swpnotice/swpcommboardServlet_file_20211025110110197_1_.jpg",
          link: "https://www.ssafy.com/ksp/servlet/swp.board.controller.SwpBoardServlet?p_process=select-board-view&p_menu_cd=M0202&p_tabseq=226507&p_seq=82&p_pageno=",
        },
        {
          src: "https://www.ssafy.com/gate/service/swpcommboard/swpnotice/swpcommboardServlet_file_20211013192126757_1_.jpg",
          link: "https://www.ssafy.com/ksp/servlet/swp.board.controller.SwpBoardServlet?p_process=select-board-view&p_menu_cd=M0202&p_tabseq=226507&p_seq=80&p_pageno=",
        },
      ],
      colors: [
        "green",
        "secondary",
        "yellow darken-4",
        "red lighten-2",
        "orange darken-1",
      ],
      cycle: false,
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
    };
  },
  methods: {
    moveLink(link) {
      window.open(link);
    },
  },
};
</script>

<style></style>
