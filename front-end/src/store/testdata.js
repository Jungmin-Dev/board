export default {
  User: [
    {
      user_id: 1,
      name: "Kim",
      created_at: "2018-09-11 11:42:11",
    },
    {
      user_id: 2,
      name: "Jung",
      created_at: "2019-09-11 11:42:11",
    },
    {
      user_id: 3,
      name: "Min",
      created_at: "2020-09-11 11:42:11",
    },
  ],
  Content:[
    {
      content_id: 1,
      user_id: 1,
      title: 'Kim의 강의 알람표',
      context: 'Kim의 강의 알람표입니다.',
      created_at: '2021-01-01 13:11:42',
      updated_at: null,
    },
    {
      content_id: 2,
      user_id: 3,
      title: 'Min의 강의 알람표',
      context: 'Min의 강의 알람표입니다.',
      created_at: '2023-01-02 13:16:42',
      updated_at: null,
    },
    {
      content_id: 3,
      user_id: 2,
      title: 'Jung의 강의 알람표',
      context: 'Jung의 강의 알람표입니다.',
      created_at: '2023-01-01 13:15:42',
      updated_at: null,
    },
  ],
  Comment: [
    {
      comment_id: 1,
      user_id: 1,
      content_id: 3,
      context: '생일 축하해요!',
      created_at: '2019-03-29 14:11:11',
      update_at: null,
    },
    {
      comment_id: 2,
      user_id: 3,
      content_id: 3,
      context: '추카추카추카추카',
      created_at: '2020-03-29 14:30:11',
      update_at: null,
    },
    {
      comment_id: 3,
      user_id: 2,
      content_id: 1,
      context: '푸카푸카',
      created_at: '2020-03-29 14:50:11',
      update_at: null,
    },
  ],
  SubComment:[
    {
      subcomment_id: 1,
      comment_id: 3,
      user_id: 1,
      context: "푸카푸카는~~~~~~",
      create_at: '2022-03-29 15:33:12',
      update_at: null,
    }
  ]
}
