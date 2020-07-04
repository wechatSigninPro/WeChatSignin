// pages/index/course/course.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    id: -1,
    course: null,
    student_course_id: null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user,
      id: options.id
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
    this.getCourse()
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

  // 获取课程
  getCourse: function () {
    util._post_auth({
      path: "/course/view",
      data: {
        "id": this.data.id
      },
      token: app.token,
      success: res => {
        if (this.data.user.ck.role == "TEACHER") {
          this.setData({
            course: res.data.data
          })
        } else {
          this.setData({
            course: res.data.data.course
          })
          if (res.data.data.id) {
            this.setData({
              student_course_id: res.data.data.id
            })
          }
        }
      },
      login: app.login
    })
  },

  // 删除课程
  deleteCourse: function () {
    util._post_auth({
      path: "/course/delete",
      data: {
        "id": this.data.id
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "删除成功",
          icon: "success"
        })
      },
      login: app.login
    })
  },

  // 申请课程
  applyCourse: function () {
    util._post_auth({
      path: "/course/application/apply",
      data: {
        "course_id": this.data.id
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "申请成功",
          icon: "success"
        })
      },
      login: app.login
    })
  },

  // 退出课程
  quitCourse: function () {
    util._post_auth({
      path: "/course/quit",
      data: {
        "id": this.data.id
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "退出成功",
          icon: "success"
        })
      },
      login: app.login
    })
  }
})