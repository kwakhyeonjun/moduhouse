<template>
  <v-container fluid>
    <v-sparkline
      :fill="fill"
      :line-width="width"
      :padding="padding"
      :smooth="radius || false"
      :value="value"
      auto-draw
    >
      <template v-slot:label="item"> {{ item.value }} </template>
    </v-sparkline>
    <v-card-subtitle class="text-center"> 일일 방문자 (명) </v-card-subtitle>

    <v-divider></v-divider>

    <v-row>
      <v-col cols="12" md="6">
        <v-row class="fill-height" align="center"> </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      fill: false,
      padding: 8,
      radius: 10,
      value: [0, 2, 5, 9, 5, 10, 3, 5, 1, 8, 2, 9, 0],

      width: 2,
    };
  },
};
</script>

<style></style>
