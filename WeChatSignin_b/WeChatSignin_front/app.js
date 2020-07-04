// app.js
// 注册一个小程序
var util = require("utils/util.js")
App({
  // 监听小程序初始化（全局只触发一次）
  onLaunch: function () {
    // 获取token缓存
    this.token = wx.getStorageSync("token")
    if (this.token) {
      // 获取user缓存
      this.user.wx = wx.getStorageSync("user.wx")
      this.user.ck = wx.getStorageSync("user.ck")
      this.authorize_wx_user_info = wx.getStorageSync("authorize_wx_user_info") ? true : false
      
      if (this.authorize_wx_user_info) {
        this.getWxUser()
      }
    } else {
      this.login()
    }
  },
  // 监听小程序显示
  onShow: function () {

  },
  // 监听小程序隐藏
  onHide: function () {

  },
  // 错误监听函数
  onError: function (msg) {
    console.info(msg)
  },

  // 登录，获取code
  login: function () {
    wx.login({
      success: res => {
        this.getToken(res.code)
      }
    })
  },

  // 获取token
  getToken: function (code) {
    util._post({
      path: "/user/login",
      data: {
        "code": code
      },
      success: res => {
        this.token = res.data.data.token
        // 缓存token
        wx.setStorage({
          key: 'token',
          data: this.token
        })
        // 同步获取user.ck
        this.getCkUser()
      }
    })
  },

  // 获取user.ck
  getCkUser: function () {
    util._get_auth({
      path: "/user/info",
      token: this.token,
      success: res => {
        this.user.ck = res.data.data
        // 缓存user.ck
        wx.setStorage({
          key: 'user.ck',
          data: this.user.ck
        })
      },
      login: this.login
    })
  },

  // 获取user.wx
  getWxUser: function (content = null) {
    wx.getUserInfo({
      success: res => {
        this.user.wx = res.userInfo
        this.authorize_wx_user_info = true
        // 缓存user.wx
        wx.setStorage({
          key: 'user.wx',
          data: this.user.wx
        })
      },
      fail: res => {
        this.authorize_wx_user_info = false
        wx.showToast({
          title: "获取微信用户信息失败",
          icon: "none"
        })
      },
      complete: res => {
        if (content) {
          content.callback(res)
        }
        // 缓存user.wx
        wx.setStorage({
          key: 'authorize_wx_user_info',
          data: this.authorize_wx_user_info
        })
      }
    })
  },

  user: {
    wx: {},
    ck: {}
  },
  authorize_wx_user_info: false,
  token: null
})