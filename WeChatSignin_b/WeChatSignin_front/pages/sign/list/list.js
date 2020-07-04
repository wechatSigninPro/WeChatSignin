// pages/sign/list/list.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    id: "",
    name: "",
    code: "",
    place: "",
    time: "",
    create_time: "",
    student_num: "",
    students: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user,
      id: options.id,
      name: options.name,
      code: options.code,
      place: options.place,
      time: options.time,
      create_time: options.create_time,
      student_num: options.student_num,
      teacher_name: options.teacher_name
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getStudents()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

    // 学生
  getStudents: function () {
    console.log("id: "+this.data.id)
    util._post_auth({
      path: "/call/list/student",
      data: {
        "id": this.data.id
      },
      token: app.token,
      success: res => {
        this.setData({
          students: res.data.data
        })
        console.log("length: "+this.data.students.length)
      },
      login: app.login
    })
  }
})