// pages/index/update/update.js
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
      time: "",
      place: ""
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
        time: options.time,
        place: options.place
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

  inputName: function (e) {
    this.setData({
      name: e.detail.value
    })
  },

  inputCode: function (e) {
    this.setData({
      code: e.detail.value
    })
  },

  inputTime: function (e) {
    this.setData({
      time: e.detail.value
    })
  },

  inputPlace: function (e) {
    this.setData({
      place: e.detail.value
    })
  },

  submit: function () {
    var remind = "";
    if (!this.data.name) {
      remind += "名称、"
    }
    if (!this.data.code) {
      remind += "课程代码、"
    }
    if (!this.data.time) {
      remind += "时间、"
    }
    if (!this.data.place) {
      remind += "地点、"
    }
    if (remind) {
      remind = remind.substr(0, remind.length - 1) + "不能为空"
      wx.showToast({
        title: remind,
        icon: "none"
      })
    } else {
      util._post_auth({
        path: "/course/update",
        data: {
          "id": this.data.id,
          "name": this.data.name,
          "code": this.data.code,
          "time": this.data.time,
          "place": this.data.place,
        },
        token: app.token,
        success: res => {
          wx.showToast({
            title: "修改成功",
            icon: "success"
          })
        },
        login: app.login
      })
    }
  }
})