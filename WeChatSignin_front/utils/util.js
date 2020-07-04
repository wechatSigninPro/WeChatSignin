const host = "http://localhost:8080/api"

// 发送post请求，参数：{path:"", data:{}, success: res => {}}
function _post(content) {
  wx.request({
    url: host + content.path,
    header: {
      "Content-Type": "application/json"
    },
    method: "POST",
    dataType: "json",
    responseType: "text",
    data: content.data,
    success: res => {
      if (res.data.code == 200) {
        content.success(res)
      } else {
        console.warn("请求失败，代码：" + res.data.code + "，信息：" + res.data.msg)
      }
    },
    fail: res => {
      console.warn("小程序发送请求失败")
    }
  })
}

// 发送需认证的get请求，参数：{path:"", token: "", success: res => {}, login: function(){}}
function _get_auth(content) {
  wx.request({
    url: host + content.path,
    header: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + content.token
    },
    method: "GET",
    dataType: "json",
    responseType: "text",
    success: res => {
      if (res.data.code == 200) {
        content.success(res)
      } else if (res.data.code == 306) {
        // token过期，重新登录
        content.login()
      } else {
        console.warn("请求失败，代码：" + res.data.code + "，信息：" + res.data.msg)
      }
    },
    fail: res => {
      console.warn("小程序发送请求失败")
    }
  })
}

// 发送需认证的post请求，参数：{path:"", token:"", data:{}, success: res => {}, login: function(){}}
function _post_auth(content) {
  wx.request({
    url: host + content.path,
    header: {
      "Content-Type": "application/json",
      "Authorization": "Bearer " + content.token
    },
    method: "POST",
    dataType: "json",
    responseType: "text",
    data: content.data,
    success: res => {
      if (res.data.code == 200) {
        content.success(res)
      } else if (res.data.code == 306) {
        // token过期，重新登录
        content.login()
        wx.showToast({
          title: "认证过期，正在重新登录",
          icon: "none"
        })
      } else {
        console.warn("请求失败，代码：" + res.data.code + "，信息：" + res.data.msg)
        wx.showToast({
          title: res.data.msg,
          icon: "none"
        })
        if (res.data.data) {
          for (var key in res.data.data) {
            console.warn(key + ": " + res.data.data[key])
            wx.showToast({
              title: res.data.data[key],
              icon: "none"
            })
          }
        }
      }
    },
    fail: res => {
      console.warn("小程序发送请求失败")
    }
  })
}

// 发送需认证的get请求下载，参数：{path:"", token: "", success: res => {}, login: function(){}}
function _download_auth(content) {
  wx.downloadFile({
    url: host + content.path,
    header: {
      "Authorization": "Bearer " + content.token
    },
    success: res => {
      if (res.statusCode == 200) {
        content.success(res)
      } else {
        wx.showToast({
          title: "导出失败，无签到数据",
          icon: "none"
        })
      }
    },
    fail: res => {
      console.warn("小程序发送请求失败")
    }
  })
}

module.exports = {
  _post: _post,
  _get_auth: _get_auth,
  _post_auth: _post_auth,
  _download_auth: _download_auth
}