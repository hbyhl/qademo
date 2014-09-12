<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properities -->
  <title>问答产品的信息提取及自动回复</title>

<!--  <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700|Open+Sans:300italic,400,300,700' rel='stylesheet' type='text/css'> -->

   <link rel="stylesheet" type="text/css" href="/css/semantic.css">
  <link rel="stylesheet" type="text/css" href="/css/homepage.css">

  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.js"></script>
  <script src="/js/semantic.js"></script>
  <script src="/js/homepage.js"></script>

</head>
<body id="home">
  <div class="ui inverted page grid masthead segment">
    <div class="column">
	<img src="/images/focus.jpg" class="ui medium image">
      <div class="inverted secondary pointing ui menu">
        <div class="header item"> T&P实习生成长计划成果汇报</div>
      </div>

      <div class="ui hidden transition information">
        <h2 class="ui inverted header">
            &nbsp;&nbsp;&nbsp; 问答产品的信息提取及自动回复系统展示
        </h2>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本系统包括问题过滤、问题分类、推荐答案及白词库维护四部分功能。</p>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="large basic inverted animated fade ui button">
          <div class="visible content">***友情帮助***</div>
          <div class="hidden content">王仲玉/吉丁丁/黄雁</div>
        </div>
      </div>
    </div>
  </div>
  <div class="ui page grid overview segment">
    <div class="ui two wide column"></div>
    <div class="twelve wide column">
      <div class="ui three column center aligned stackable divided grid">
        <div class="column">
          <div class="ui icon header">
            <i class="circular book link icon"></i>
                                     维护白词库 
          </div>
          <p>通过对编辑已回答的有意义问题进行分析，获得可添加到白词库中的白词，以供编辑维护白词库</p>
          <p><a class="ui teal right labeled icon button" href="/whiteword/addwhiteword">功能展示 <i class="right long arrow icon"></i></a></p>
        </div>
        <div class="column">
          <div class="ui icon header">
            <i class="circular code link icon"></i>
                                    问题过滤及自动问答
          </div>
          <p>对给定问题进行判定是否有意义，判定属于哪一类，以及该类问题的匹配问题。</p>
          <p><a class="ui teal right labeled icon button" href="/qademo/autoanswer">功能展示 <i class="right long arrow icon"></i></a></p>
        </div>
        <div class="column">
          <div class="ui icon header">
            <i class="circular user link icon"></i>
                                     小组成员
          </div>
          <p>搜狐小伙伴们</p>
          <p><a class="ui teal right labeled icon button" href="#">成员介绍 <i class="right long arrow icon"></i></a></p>
        </div>
      </div>
    </div>
  </div>

  <div class="ui inverted teal page grid segment">
      <div class="ui three column stackable grid">
        <div style="text-align:center;">
           <h3 class="ui header">所有参与项目人员：王仲玉/吉丁丁/黄雁/杨会龙/薛庆元/谷丰/范晓亮/黎寒玉/张新</h3>
           <p>2014年7月25日-2014年9月4日</p>
        </div>
      </div>
  </div>
</body>

</html>
