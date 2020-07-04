// pages/me/info/info.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    department: "",
    phone_num: "",
    email: "",
    major: "",
    grade: "",
    clazz: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user,
      department: app.user.ck.department ? app.user.ck.department : "",
      phone_num: app.user.ck.phone_num ? app.user.ck.phone_num : "",
      email: app.user.ck.email ? app.user.ck.email : "",
      major: app.user.ck.student && app.user.ck.student.major ? app.user.ck.student.major : "",
      grade: app.user.ck.student && app.user.ck.student.grade ? app.user.ck.student.grade : "",
      clazz: app.user.ck.student && app.user.ck.student.clazz ? app.user.ck.student.clazz : ""
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

  inputDepartment: function (e) {
    this.setData({
      department: e.detail.value
    })
  },

  inputPhoneNum: function (e) {
    this.setData({
      phone_num: e.detail.value
    })
  },

  inputEmail: function (e) {
    this.setData({
      email: e.detail.value
    })
  },

  inputMajor: function (e) {
    this.setData({
      major: e.detail.value
    })
  },

  inputGrade: function (e) {
    this.setData({
      grade: e.detail.value
    })
  },

  inputClazz: function (e) {
    this.setData({
      clazz: e.detail.value
    })
  },

  submit: function () {
    var remind = "";
    if (this.data.phone_num) {
      var phone_num_reg = /^[1][3,4,5,7,8][0-9]{9}$/;
      if (!phone_num_reg.test(this.data.phone_num)) {
        remind += "手机号、"
      }
    }
    if (this.data.email) {
      var email_reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
      if (!email_reg.test(this.data.email)) {
        remind += "邮箱、"
      }
    }
    if (remind) {
      remind = remind.substr(0, remind.length - 1) + "格式错误"
      wx.showToast({
        title: remind,
        icon: "none"
      })
    } else {
      var form_data = this.data.user.ck.role == "STUDENT" ? {
        "department": this.data.department,
        "phone_num": this.data.phone_num,
        "email": this.data.email,
        "major": this.data.major,
        "grade": this.data.grade,
        "clazz": this.data.clazz
      } : {
          "department": this.data.department,
          "phone_num": this.data.phone_num,
          "email": this.data.email
        }
      util._post_auth({
        path: "/user/more",
        data: form_data,
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