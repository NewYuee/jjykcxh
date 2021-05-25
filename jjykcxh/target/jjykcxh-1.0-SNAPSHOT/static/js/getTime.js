
window.onload="startTime()";
setTimeout(startTime,0);
function startTime(){
    var today=new Date();
    var weekday=new Array(7);
	weekday[0]="周日";
	weekday[1]="周一";
	weekday[2]="周二";
	weekday[3]="周三";
	weekday[4]="周四";
	weekday[5]="周五";
	weekday[6]="周六";
    var mon=today.getMonth()+1;
    var y=today.getFullYear();
    var d=today.getDate();
	var h=today.getHours();
	var m=today.getMinutes();
	var s=today.getSeconds();// 在小于10的数字前加一个‘0’
	m=checkTime(m);
    s=checkTime(s);
    d=checkTime(d);
    mon=checkTime(mon);
    
	var week=weekday[today.getDay()];
    document.getElementById('txt').innerHTML="现在是："+y+"年"+mon+"月"+d+"日 "+week+"  "+h+":"+m+":"+s;
	t=setTimeout(function(){startTime()},1000);
}

function checkTime(i){
	if (i<10){
		i="0" + i;
	}
	return i;
}

window.onload="setDate1()";
setTimeout(setDate1,0);
function setDate1(){
    var date=new Date("2002/09/01 00:00:00");
    var date2=new Date();
    var time1=date2.getTime();
    var time2=date.getTime();
    var time=time1-time2;
    var day=24*3600*1000;
    var days=parseInt(time/day);
    var year=parseInt(days/365);
    var month=parseInt((days%365)/30);
    document.getElementById('days').innerHTML="交协已成立"+year+"年有"+month+"个月";
}