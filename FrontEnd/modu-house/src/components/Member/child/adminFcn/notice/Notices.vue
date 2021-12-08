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
          label="공지 사항 검색"
          class="mx-8"
        ></v-text-field>
      </template>
      <!--search ends here-->
      <template v-slot:item.subject="{ item }">
        <a
          @click="$router.push(`../../notice/${item.articleno}`)"
          style="text-decoration: none; color: inherit"
        >
          {{ item.subject }}
        </a>
      </template>
    </v-data-table>

    <v-col><v-col md="8" /></v-col>
    <v-row>
      <v-col md="8" />
      <v-btn @click="goodbye"> 공지 삭제 </v-btn>
      <v-btn @click="createNotice"> 공지 등록 </v-btn>
    </v-row>
  </v-container>
</template>

<script>
//import { listArticleFreeBoard, deleteArticleFreeBoard } from "@/api/board";

export default {
  name: "Notices",
  methods: {
    goodbye() {
      alert("삭제하시겠습니까?");
      //  deleteArticleFreeBoard(this.checked);
      console.log(this.checked);
      alert("게시글 삭제 완료");
      location.reload();
    },
    //select 시 userid 가져오는거 확인. 이거로 redirect
    enterSelect() {
      console.log(this.selected.map((e) => e.articleno));
      this.checked = this.selected.map((e) => e.articleno);
    },
    createNotice() {
      this.$router.push({ name: "NoticeCreate" });
    },
  },

  data() {
    return {
      checked: "",
      search: "",
      page: "",
      headers: [
        { text: "번호", value: "articleno", width: "5px", align: "center" },
        { text: "제목", value: "subject", width: "90%", align: "center" },
        { text: "내용", value: "content", align: " d-none" },
      ],

      boards: [
        {
          articleno: 1,
          subject: "이용약관",
          content: "내용1",
        },
        {
          articleno: 2,
          subject: "개인정보 처리방침",
          content: "내용2",
        },
      ],
    };
  },

  /*
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
  },*/
};
</script>

<style></style>
