<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="desserts"
        :items-per-page="10"
        class="elevation-1"
    >

    </v-data-table>
  </div>
</template>

<script>
export default{
  name: 'GreetViews',
  data () {
    return {
      headers: [
        {
          text: '순번',
          align: 'start',
          sortable: false,
          value: 'number',
        },
        { text: '게시글 제목', value: 'title' },
        { text: '작성자', value: 'name' },
        { text: '작성일', value: 'date' },
        { text: '조회수', value: 'lookup' },
      ],
      desserts: [
        {
          number: 1,
          title: '안녕하세염',
          name: '김정민',
          date: new Date,
          lookup: 24,
        },
        {
          number: 2,
          title: '방가',
          name: '김정민',
          date: new Date,
          lookup: 240,
        },
      ],
    }
  },
}
</script>

<style scoped>

</style>
