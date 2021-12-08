<template>
  <v-container>
    <v-data-table
      v-model="selected"
      @input="enterSelect()"
      :headers="headers"
      :items="boards"
      single-select
      item-key="articleno"
      show-select
      class="elevation-1"
      :search="search"
    >
      <!-- 나중에 시간 나면 :single-select="single-select"로 바꾸고 multiple delete로..-->
      <template v-slot:top>
        <v-text-field
          v-model="search"
          label="게시글 번호,아이디,제목 검색"
          class="mx-8"
        ></v-text-field>
      </template>
      <!--search ends here-->
      <template #item.articleno="{ value }">
        <router-link :to="`/board/free/detail/${value}`">{{
          value
        }}</router-link>
      </template>
    </v-data-table>

    <v-col><v-col md="8" /></v-col>
    <v-container>
      <v-row>
        <v-col md="6" />
        <v-btn @click="goodbye"> 게시글 삭제 </v-btn>
      </v-row>
    </v-container>
  </v-container>
</template>

<script>
import { listArticleFreeBoard, deleteArticleFreeBoard } from "@/api/board";

export default {
  name: "BoardList",
  methods: {
    goodbye() {
      alert("삭제하시겠습니까?");
      deleteArticleFreeBoard(this.checked);
      console.log(this.checked);
      alert("게시글 삭제 완료");
      location.reload();
    },
    //select 시 userid 가져오는거 확인
    enterSelect() {
      console.log(this.selected.map((e) => e.articleno));
      this.checked = this.selected.map((e) => e.articleno);
    },
  },

  data() {
    return {
      checked: "",
      search: "",
      page: "",
      headers: [
        {
          text: "no",
          align: "start",
          sortable: true,
          value: "articleno",
        },
        { text: "아이디", value: "userid" },
        { text: "제목", value: "subject" },
        { text: "조회수", value: "hit" },
        { text: "등록 날짜", value: "regtime" },
      ],

      boards: [],
    };
  },
  created() {
    let param = {
      pg: 1,
      spp: 20,
      key: null,
      word: null,
    };
    listArticleFreeBoard(
      param,
      (response) => {
        this.boards = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
};
</script>

<style></style>
