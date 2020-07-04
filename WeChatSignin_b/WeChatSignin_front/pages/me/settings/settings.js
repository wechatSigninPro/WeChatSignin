// pages/me/settings/settings.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    role: [
      {
        value: "STUDENT",
        description: "学生",
        campus_id_desc: "学号"
      },
      {
        value: "TEACHER",
        description: "老师",
        campus_id_desc: "工号"
      }],
    index: 0,
    name: "",
    school: "",
    campus_id: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user,
      index: app.user.ck.role == "TEACHER" ? 1 : 0,
      name: app.user.ck.name ? app.user.ck.name : "",
      school: app.user.ck.school ? app.user.ck.school : "",
      campus_id: app.user.ck.campus_id ? app.user.ck.campus_id : ""
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

  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    })
  },

  inputName: function (e) {
    this.setData({
      name: e.detail.value
    })
  },

  inputSchool: function (e) {
    this.setData({
      school: e.detail.value
    })
  },

  inputCampusId: function (e) {
    this.setData({
      campus_id: e.detail.value
    })
  },

  submit: function () {
    var remind = "";
    if (!this.data.name) {
      remind += "姓名、"
    }
    if (!this.data.school) {
      remind += "学校、"
    }
    if (!this.data.campus_id) {
      remind += this.data.role[this.data.index].campus_id_desc + "、"
    }
    if (remind) {
      remind = remind.substr(0, remind.length - 1) + "不能为空"
      wx.showToast({
        title: remind,
        icon: "none"
      })
    } else {
      util._post_auth({
        path: "/user/info",
        data: {
          "role": this.data.role[this.data.index].value,
          "name": this.data.name,
          "school": this.data.school,
          "campus_id": this.data.campus_id,
        },
        token: app.token,
        success: res => {
          app.user.ck = res.data.data
          wx.showToast({
            title: "保存成功",
            icon: "success"
          })
          // 缓存user.ck
          wx.setStorage({
            key: 'user.ck',
            data: app.user.ck
          })
        },
        login: app.login
      })
    }
  }
})