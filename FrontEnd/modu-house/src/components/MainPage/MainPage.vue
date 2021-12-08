<template>
  <v-main>
    <v-container>
      <v-row>
        <v-col cols="12" lg="2" sm="2" />

        <v-col cols="12" lg="8" sm="8">
          <v-row>
            <v-text-field
              class="mt-15"
              label="🔍"
              v-model="searchWord"
              placeholder="검색어 입력"
              filled
              rounded
              dense
              @keyup.enter="move"
            ></v-text-field>

            <!-- 광고 -->
            <v-col cols="3">
              <v-sheet min-height="100" min-width="270">
                <!-- 
                   -->
                <advertisement />
              </v-sheet>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4.5">
              <v-row>
                <!--
                <free-board-list type="free" />
                  -->
                <recent-board-list-util type="free" />
              </v-row>
              <v-row>
                <!-- 

                <data-board-list type="data" />
                -->
                <recent-board-list-util type="data" />
              </v-row>
            </v-col>
            <v-col cols="4.5">
              <v-row>
                <recent-board-list-util type="local" />
                <!-- 

                <board-list-util2 type="local" />
                -->
              </v-row>
              <v-row>
                <recent-board-list-util type="share" />
                <!--
                <share-board-list type="share" />
                  -->
              </v-row>
            </v-col>
            <v-col cols="3"
              ><v-sheet>
                <!-- 
                   -->
                <best-area />
              </v-sheet>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12" lg="2" sm="2" />
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
/* 게시판 리스트 */
/*
import FreeBoardList from "@/components/Board/FreeBoard/child/BoardList.vue";
import DataBoardList from "@/components/Board/DataBoard/child/BoardList.vue";
import LocalBoardList from "@/components/Board/LocalBoard/child/BoardList.vue";
import ShareBoardList from "@/components/Board/ShareBoard/child/BoardList.vue";
*/
/* 오른쪽 영역 컨텐츠  */
import Advertisement from "./child/Advertisement.vue";
import BestArea from "./child/BestArea.vue";
import RecentBoardListUtil from "@/components/Board/BoardList/RecentBoardListUtil.vue";
import { mapMutations, mapState } from "vuex";

export default {
  data() {
    return {
      searchWord: "",
    };
  },
  components: {
    /*FreeBoardList,
    DataBoardList,
    LocalBoardList,
    ShareBoardList,*/
    RecentBoardListUtil,
    BestArea,
    Advertisement,
  },
  computed: {
    ...mapState({ sendsearchWord: "mainSearchWord" }),
    mainSearchWord: {
      get() {
        return this.sendsearchWord;
      },
      set(newWord) {
        return newWord;
      },
    },
  },
  methods: {
    ...mapMutations(["SET_MAIN_SEARCH_WORD"]),
    move() {
      this.SET_MAIN_SEARCH_WORD(this.searchWord);
      this.$router.push({ name: "MapSearchName" });
    },
  },
};
</script>

<style></style>
