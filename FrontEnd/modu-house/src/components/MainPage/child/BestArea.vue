<template>
  <v-card>
    <v-list two-line>
      <v-list-item-group v-model="selected" active-class="pink--text">
        <v-list-item-content>
          <v-list-item-title class="ml-3">실시간 인기지역</v-list-item-title>
        </v-list-item-content>
        <template v-for="(item, index) in items">
          <v-list-item :key="item.aptName">
            <v-icon color="grey lighten-1" class="mr-2">
              mdi-numeric-{{ index + 1 }}
            </v-icon>

            <v-list-item-content>
              <v-list-item-title v-text="item.aptName" @click="moveMap" />
              <v-list-item-subtitle
                >{{ item.sidoName }} {{ item.gugunName }}
                {{ item.dongName }}</v-list-item-subtitle
              >
            </v-list-item-content>

            <v-list-item-action v-if="item.count % 2 === 0">
              <v-icon color="red lighten-1" size="20">
                <!-- mdi-arrow-up-thin -->
                mdi-menu-up
              </v-icon>
            </v-list-item-action>
            <v-list-item-action v-if="item.count % 2 === 1">
              <v-icon color="blue lighten-1" size="20">
                <!-- mdi-arrow-down-thin -->
                mdi-menu-down
              </v-icon>
            </v-list-item-action>
          </v-list-item>

          <v-divider v-if="index < items.length - 1" :key="index"></v-divider>
        </template>
      </v-list-item-group>
    </v-list>
  </v-card>
</template>

<script>
import { listInterest } from "@/api/bestarea.js";
export default {
  data: () => ({
    selected: [2],
    items: [],
  }),
  created() {
    listInterest(
      (response) => {
        this.items = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveMap() {},
  },
};
</script>

<style></style>
