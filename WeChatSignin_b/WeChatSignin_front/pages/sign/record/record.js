// pages/sign/record/record.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    records: [],
    id: "",
    name: "",
    code: "",
    teacher_name: "",
    place: "",
    time: ""
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
      teacher_name: options.teacher_name,
      place: options.place,
      time: options.time
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
    this.getRecords()
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

  // 获取签到记录
  getRecords: function () {
    util._post_auth({
      path: "/call/list/course",
      data: {
        "course_id": this.data.id
      },
      token: app.token,
      success: res => {
        this.setData({
          records: res.data.data
        })
      },
      login: app.login
    })
  }
})