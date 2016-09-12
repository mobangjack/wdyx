<%@page import="com.wdyx.weixin.web.ticket.TicketUtil"%>
<%@page import="com.wdyx.weixin.web.ticket.Ticket"%>
<%@page import="java.util.List;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Ticket ticket = TicketUtil.getTicket();
    %>
<!DOCTYPE html>
<html>
<head>
<title><%=ticket.getTitle()%>抢票平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/ticket.css" rel="stylesheet"></link>
<style type="text/css">
.posimg {
	background-image: url("${baseViewPicUrl}<%=ticket.getPicurl()%>");
}
</style>
</head>

<body>
	<input type="hidden" id="openid" value="${openid }">
	<div id="header" align="center"><strong>距【<%=ticket.getTitle()%>】抢票时间还有</strong>
		<div class="t1" id="<%=System.currentTimeMillis()%>"></div>
		<div class="t2" id="<%=ticket.getDate().getTime()%>"></div>
	</div>
	
	<div id="content">
		<div id="line0" class="line">
			<div id="pos1" class="pos poss1 posimg uncheck"></div>
			<div id="pos2" class="pos poss2 posimg uncheck"></div>
			<div id="pos3" class="pos poss3 posimg uncheck"></div>
			<div id="pos4" class="pos poss4 posimg uncheck"></div>
			<div id="pos5" class="pos poss5 posimg uncheck"></div>
			<div id="pos6" class="pos poss6 posimg uncheck"></div>
			<div id="pos7" class="pos poss7 posimg uncheck"></div>
			<div id="pos8" class="pos poss8 posimg uncheck"></div>
			<div id="pos9" class="pos poss9 posimg uncheck"></div>
			<div id="pos10" class="pos poss10 posimg uncheck"></div>
		</div>
		<div id="line1" class="line">
			<div id="pos11" class="pos poss11 posimg uncheck"></div>
			<div id="pos12" class="pos poss12 posimg uncheck"></div>
			<div id="pos13" class="pos poss13 posimg uncheck"></div>
			<div id="pos14" class="pos poss14 posimg uncheck"></div>
			<div id="pos15" class="pos poss15 posimg uncheck"></div>
			<div id="pos16" class="pos poss16 posimg uncheck"></div>
			<div id="pos17" class="pos poss17 posimg uncheck"></div>
			<div id="pos18" class="pos poss18 posimg uncheck"></div>
			<div id="pos19" class="pos poss19 posimg uncheck"></div>
			<div id="pos20" class="pos poss20 posimg uncheck"></div>
		</div>
		<div id="line2" class="line">
			<div id="pos21" class="pos poss21 posimg uncheck"></div>
			<div id="pos22" class="pos poss22 posimg uncheck"></div>
			<div id="pos23" class="pos poss23 posimg uncheck"></div>
			<div id="pos24" class="pos poss24 posimg uncheck"></div>
			<div id="pos25" class="pos poss25 posimg uncheck"></div>
			<div id="pos26" class="pos poss26 posimg uncheck"></div>
			<div id="pos27" class="pos poss27 posimg uncheck"></div>
			<div id="pos28" class="pos poss28 posimg uncheck"></div>
			<div id="pos29" class="pos poss29 posimg uncheck"></div>
			<div id="pos30" class="pos poss30 posimg uncheck"></div>
		</div>
		<div id="line3" class="line">
			<div id="pos31" class="pos poss31 posimg uncheck"></div>
			<div id="pos32" class="pos poss32 posimg uncheck"></div>
			<div id="pos33" class="pos poss33 posimg uncheck"></div>
			<div id="pos34" class="pos poss34 posimg uncheck"></div>
			<div id="pos35" class="pos poss35 posimg uncheck"></div>
			<div id="pos36" class="pos poss36 posimg uncheck"></div>
			<div id="pos37" class="pos poss37 posimg uncheck"></div>
			<div id="pos38" class="pos poss38 posimg uncheck"></div>
			<div id="pos39" class="pos poss39 posimg uncheck"></div>
			<div id="pos40" class="pos poss40 posimg uncheck"></div>
		</div>
		<div id="line4" class="line">
			<div id="pos41" class="pos poss41 posimg uncheck"></div>
			<div id="pos42" class="pos poss42 posimg uncheck"></div>
			<div id="pos43" class="pos poss43 posimg uncheck"></div>
			<div id="pos44" class="pos poss44 posimg uncheck"></div>
			<div id="pos45" class="pos poss45 posimg uncheck"></div>
			<div id="pos46" class="pos poss46 posimg uncheck"></div>
			<div id="pos47" class="pos poss47 posimg uncheck"></div>
			<div id="pos48" class="pos poss48 posimg uncheck"></div>
			<div id="pos49" class="pos poss49 posimg uncheck"></div>
			<div id="pos50" class="pos poss50 posimg uncheck"></div>
		</div>
		<div id="line5" class="line">
			<div id="pos51" class="pos poss51 posimg uncheck"></div>
			<div id="pos52" class="pos poss52 posimg uncheck"></div>
			<div id="pos53" class="pos poss53 posimg uncheck"></div>
			<div id="pos54" class="pos poss54 posimg uncheck"></div>
			<div id="pos55" class="pos poss55 posimg uncheck"></div>
			<div id="pos56" class="pos poss56 posimg uncheck"></div>
			<div id="pos57" class="pos poss57 posimg uncheck"></div>
			<div id="pos58" class="pos poss58 posimg uncheck"></div>
			<div id="pos59" class="pos poss59 posimg uncheck"></div>
			<div id="pos60" class="pos poss60 posimg uncheck"></div>
		</div>
		<div id="line6" class="line">
			<div id="pos61" class="pos poss61 posimg uncheck"></div>
			<div id="pos62" class="pos poss62 posimg uncheck"></div>
			<div id="pos63" class="pos poss63 posimg uncheck"></div>
			<div id="pos64" class="pos poss64 posimg uncheck"></div>
			<div id="pos65" class="pos poss65 posimg uncheck"></div>
			<div id="pos66" class="pos poss66 posimg uncheck"></div>
			<div id="pos67" class="pos poss67 posimg uncheck"></div>
			<div id="pos68" class="pos poss68 posimg uncheck"></div>
			<div id="pos69" class="pos poss69 posimg uncheck"></div>
			<div id="pos70" class="pos poss70 posimg uncheck"></div>
		</div>
		<div id="line7" class="line">
			<div id="pos71" class="pos poss71 posimg uncheck"></div>
			<div id="pos72" class="pos poss72 posimg uncheck"></div>
			<div id="pos73" class="pos poss73 posimg uncheck"></div>
			<div id="pos74" class="pos poss74 posimg uncheck"></div>
			<div id="pos75" class="pos poss75 posimg uncheck"></div>
			<div id="pos76" class="pos poss76 posimg uncheck"></div>
			<div id="pos77" class="pos poss77 posimg uncheck"></div>
			<div id="pos78" class="pos poss78 posimg uncheck"></div>
			<div id="pos79" class="pos poss79 posimg uncheck"></div>
			<div id="pos80" class="pos poss80 posimg uncheck"></div>
		</div>
		<div id="line8" class="line">
			<div id="pos81" class="pos poss81 posimg uncheck"></div>
			<div id="pos82" class="pos poss82 posimg uncheck"></div>
			<div id="pos83" class="pos poss83 posimg uncheck"></div>
			<div id="pos84" class="pos poss84 posimg uncheck"></div>
			<div id="pos85" class="pos poss85 posimg uncheck"></div>
			<div id="pos86" class="pos poss86 posimg uncheck"></div>
			<div id="pos87" class="pos poss87 posimg uncheck"></div>
			<div id="pos88" class="pos poss88 posimg uncheck"></div>
			<div id="pos89" class="pos poss89 posimg uncheck"></div>
			<div id="pos90" class="pos poss90 posimg uncheck"></div>
		</div>
		<div id="line9" class="line">
			<div id="pos91" class="pos poss91 posimg uncheck"></div>
			<div id="pos92" class="pos poss92 posimg uncheck"></div>
			<div id="pos93" class="pos poss93 posimg uncheck"></div>
			<div id="pos94" class="pos poss94 posimg uncheck"></div>
			<div id="pos95" class="pos poss95 posimg uncheck"></div>
			<div id="pos96" class="pos poss96 posimg uncheck"></div>
			<div id="pos97" class="pos poss97 posimg uncheck"></div>
			<div id="pos98" class="pos poss98 posimg uncheck"></div>
			<div id="pos99" class="pos poss99 posimg uncheck"></div>
			<div id="pos100" class="pos poss100 posimg uncheck"></div>
		</div>
	</div>
	<br>
	<div align="center">
	<input type="button" class="btn48" id="btn" value="开始抢票">
	</div>
	<br>
	<br>
	<!-- javascript -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/ticket.js"></script>
</body>
</html>