// pages/sign/call/call.js
var util = require("../../../utils/util.js")
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    user: {},
    courses: [],
    index: -1,
    password: "",
    latitude: 0,
    longitude: 0,
    calls: [],
    num: -1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.user
    })
    if (this.data.user.ck && this.data.user.ck.role) {
      this.getCourses()
      this.getLocation()
    }
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

  // 获取课程
  getCourses: function () {
    util._get_auth({
      path: "/course/list/course",
      token: app.token,
      success: res => {
        this.setData({
          courses: res.data.data
        })
        if (this.data.courses.length > 0) {
          this.setData({
            index: 0
          })
          while (this.data.password < 1000) {
            this.setData({
              password: Math.floor(Math.random() * 10000).toString()
            })
          }
        }
      },
      login: app.login
    })
  },

  // 获取位置信息
  getLocation: function (content) {
    for (var i = 0; i < 3; i++) {
      wx.getLocation({
        success: res => {
          if (res.latitude != 0 || res.longitude != 0) {
            this.setData({
              latitude: res.latitude,
              longitude: res.longitude
            })
            return
          }
        }
      })
    }
  },

  bindPickerChange: function (e) {
    this.setData({
      index: e.detail.value
    })
  },

  inputPassword: function (e) {
    this.setData({
      password: e.detail.value
    })
  },

  // 创建点名
  createCall: function () {
    if (this.data.index < 0 || this.data.index >= this.data.courses.length) {
      wx.showToast({
        title: "课程选择错误",
        icon: "none"
      })
      return
    }
    if (this.data.password.length != 4) {
      wx.showToast({
        title: "口令长度须为四位",
        icon: "none"
      })
      return
    }
    if (this.data.latitude == 0 && this.data.longitude == 0) {
      wx.getLocation({
        success: res => {
          if (res.latitude != 0 || res.longitude != 0) {
            this.setData({
              latitude: res.latitude,
              longitude: res.longitude
            })
            this.submitCall()
          } else {
            wx.showToast({
              title: "获取位置信息失败，请稍后再试",
              icon: "none"
            })
          }
        },
        fail: function () {
          wx.showToast({
            title: "获取位置信息失败，请稍后再试",
            icon: "none"
          })
        }
      })
    } else {
      this.submitCall()
    }
  },
  // 提交点名
  submitCall: function () {
    util._post_auth({
      path: "/call/create",
      data: {
        "course_id": this.data.courses[this.data.index].id,
        "password": this.data.password,
        "latitude": this.data.latitude,
        "longitude": this.data.longitude
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "点名成功",
          icon: "success"
        })
      },
      login: app.login
    })
  },
  // 查询点名
  getCalls: function (e) {
    if (e.detail.value.length == 4) {
      this.setData({
        password: e.detail.value
      })
      if (this.data.latitude == 0 && this.data.longitude == 0) {
        wx.getLocation({
          success: res => {
            if (res.latitude != 0 || res.longitude != 0) {
              this.setData({
                latitude: res.latitude,
                longitude: res.longitude
              })
              this.requestCalls()
            } else {
              wx.showToast({
                title: "获取位置信息失败，请稍后再试",
                icon: "none"
              })
            }
          },
          fail: function () {
            wx.showToast({
              title: "获取位置信息失败，请稍后再试",
              icon: "none"
            })
          }
        })
      } else {
        this.requestCalls()
      }
    }
  },
  // 提交查询点名请求
  requestCalls: function () {
    util._post_auth({
      path: "/call/list/password",
      data: {
        "password": this.data.password,
        "latitude": this.data.latitude,
        "longitude": this.data.longitude
      },
      token: app.token,
      success: res => {
        if(res.data.data.length == 0) {
          wx.showToast({
            title: "未查询到相关点名",
            icon: "none"
          })
        }else {
          this.setData({
            calls: res.data.data
          })
        }
      },
      login: app.login
    })
  },

  selected: function(e) {
    this.setData({
      num: e.currentTarget.dataset.num
    })
  },

  // 创建签到
  createSign: function () {
    if (this.data.calls.length <= 0) {
      wx.showToast({
        title: "未查询到相关点名",
        icon: "none"
      })
      return
    }
    if(this.data.calls.length == 1){
      this.setData({
        num: 0
      })
    } else if(this.data.num <0){
      wx.showToast({
        title: "请选择签到课程",
        icon: "none"
      })
      return
    }
    if (this.data.latitude == 0 && this.data.longitude == 0) {
      wx.getLocation({
        success: res => {
          if (res.latitude != 0 || res.longitude != 0) {
            this.setData({
              latitude: res.latitude,
              longitude: res.longitude
            })
            this.submitSign()
          } else {
            wx.showToast({
              title: "获取位置信息失败，请稍后再试",
              icon: "none"
            })
          }
        },
        fail: function () {
          wx.showToast({
            title: "获取位置信息失败，请稍后再试",
            icon: "none"
          })
        }
      })
    } else {
      this.submitSign()
    }
  },
  // 提交签到
  submitSign: function () {
    util._post_auth({
      path: "/sign/create",
      data: {
        "call_id": this.data.calls[this.data.num].id,
        "latitude": this.data.latitude,
        "longitude": this.data.longitude
      },
      token: app.token,
      success: res => {
        wx.showToast({
          title: "签到成功",
          icon: "success"
        })
      },
      login: app.login
    })
  }
})