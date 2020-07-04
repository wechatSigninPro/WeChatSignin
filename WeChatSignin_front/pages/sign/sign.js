// pages/sign/sign.js
var util = require("../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    calls: []
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
    this.setData({
      user: app.user
    })
    if (this.data.user.ck && this.data.user.ck.role) {
      this.getCalls()
    }
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
  getCalls: function () {
    util._get_auth({
      path: "/call/list",
      token: app.token,
      success: res => {
        this.setData({
          calls: res.data.data
        })
      },
      login: app.login
    })
  },

  // 扫一扫，完成签到
  scanCode: function () {
    if (!this.data.user.ck.role) {
      wx.showToast({
        title: "请在设置中选择你的身份",
        icon: "none"
      })
    } else {
      wx.scanCode({
        onlyFromCamera: true,
        success: (res) => {
          var content = JSON.parse(res.result)
          this.submitSign(content)
        },
        fail: (res) => {
          wx.showToast({
            title: "二维码扫描失败",
            icon: "none"
          })
        }
      })
    }
  },

  // 提交签到
  submitSign: function (content) {
    util._post_auth({
      path: "/sign/qrcode",
      data: {
        "teacher_user_id": content.teacherUserId,
        "encrypted_data": content.encryptedData
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "签到成功",
          icon: "success"
        })
        this.getCalls()
      },
      login: app.login
    })
  },

  // 导出签到为excel
  exportToExcel: function () {
    util._download_auth({
      path: "/call/export",
      token: app.token,
      success: res => {
        var filePath = res.tempFilePath
        wx.saveFile({
          tempFilePath: filePath,
          success: resp => {
            wx.showToast({
              title: "导出成功",
              icon: "none"
            })
            wx.openDocument({
              filePath: resp.savedFilePath,
              fileType: "xlsx"
            })
          },
          fail: resp => {
            wx.showToast({
              title: "导出失败",
              icon: "none"
            })
          }
        })

      },
      login: app.login
    })
  }
})