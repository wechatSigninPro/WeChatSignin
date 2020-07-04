// pages/sign/details/details.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    id: -1,
    call: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id,
      user: app.user
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
    this.getCall()
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

  // 获取点名
  getCall: function () {
    util._post_auth({
      path: "/call/view",
      data: {
        "id": this.data.id
      },
      token: app.token,
      success: res => {
        this.setData({
          call: res.data.data
        })
      },
      login: app.login
    })
  },

  // 删除点名
  deleteCall: function () {
    util._post_auth({
      path: "/call/delete",
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
  }
})