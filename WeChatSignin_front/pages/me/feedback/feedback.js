// pages/me/feedback/feedback.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  inputContent: function (e) {
    this.setData({
      content: e.detail.value
    })
  },

  submit: function () {
    if (!this.data.content) {
      wx.showToast({
        title: "反馈内容不能为空",
        icon: "none"
      })
    } else {
      util._post_auth({
        path: "/feedback/create",
        data: {
          "content": this.data.content
        },
        token: app.token,
        success: res => {
          wx.showToast({
            title: "反馈成功",
            icon: "success"
          })
        },
        login: app.login
      })
    }
  }
})