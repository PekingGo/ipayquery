<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>登录页面</title>
    <!-- 在地址栏中显示图标 -->
    <link type="image/x-icon" rel="shortcut icon" media="screen" href="${cxtPath}/img/favicon.ico" />
    <link type="image/x-icon" rel="icon" media="screen" href="${cxtPath}/img/favicon.ico" />
    <!-- 在收藏夹中显示图标 -->
    <link type="image/x-icon" rel="bookmark" media="screen" href="${cxtPath}/img/favicon.ico" />

    <!-- bootstrap -->
    <%@ include file="/WEB-INF/jsp/include/bootstrap_v3_3_2_css.jsp" %>
    <!-- normalize -->
    <link type="text/css" rel="stylesheet" media="screen" href="${cxtPath}/assets/css/normalize.css" />
    <!-- jQuery UI Theme -->
    <link type="text/css" rel="stylesheet" media="screen" href="${cxtPath}/assets/css/jqueryUI/v1_11_2/redmond/jquery-ui.css" />
    <!-- jQuery UI Theme Custom -->
    <link type="text/css" rel="stylesheet" media="screen" href="${cxtPath}/assets/css/jquery-ui-custom.css" />
    
    <link type="text/css" rel="stylesheet" media="screen" href="${cxtPath}/assets/css/login.css" />
</head>

<body>

<div class="container">
    <div class="row login-center">
        <div class="login-image"><img src="${cxtPath}/img/login.png" alt="圆角图片" /></div>
        <div class="login-input-div">
            <form class="form-horizontal" role="form" method="post" action="${cxtPath}/login">
                <div class="form-group"></div>
                <div class="form-group"></div>
                <div class="form-group"></div>
                <div class="form-group has-feedback">
                    <label for="u_account" class="col-lg-3 control-label login-input-label">用&nbsp;&nbsp;户：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="u_account" name="u_account" placeholder="请输入用户名" value="${u_account }" required autofocus/>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                </div>
                <div class="form-group"></div>
                <div class="form-group has-feedback">
                    <label for="u_password" class="col-lg-3 control-label login-input-label">密&nbsp;&nbsp;码：</label>
                    <div class="col-lg-9">
                        <input type="password" class="form-control" id="u_password" name="u_password" placeholder="请输入密码" value="${u_password }" required />
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                </div>
                <div class="form-group"></div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-8">
                        <button type="submit" class="btn btn-primary btn-block">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                    </div>
                </div>
                <div class="form-group"></div>
                <div class="form-group">
                    <label class="col-lg-12 control-label login-info-label">${errInfo}</label>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="${cxtPath}/assets/js/jQuery/v1_11_2/jquery.js"></script>
<!-- jQuery Custom -->
<script type="text/javascript" src="${cxtPath}/assets/js/jquery-custom.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="${cxtPath}/assets/js/bootstrap/v3_3_2/bootstrap.js"></script>
<!-- Bootstrap Custom -->
<script type="text/javascript" src="${cxtPath}/assets/js/bootstrap-custom.js"></script>
<!-- jQuery UI -->
<script type="text/javascript" src="${cxtPath}/assets/js/jQueryUI/v1_11_2/jquery-ui.js"></script>
<!-- jQuery UI Custom -->
<script type="text/javascript" src="${cxtPath}/assets/js/jquery-ui-custom.js"></script>

<script type="text/javascript" src="${cxtPath}/assets/js/login.js"></script>

</body>

</html>