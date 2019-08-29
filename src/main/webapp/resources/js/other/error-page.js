$(function(){
	$("#title").hide();
	var title=setTimeout(function(){
		$("#title").addClass("title_move");
		$("#title").show();
	},400);

	$(".cancle").click(function(){
		window.history.go(-1);
		return false;
	});
	var s = 15000;
	$('#second').text(s/1000);
	setInterval(setValue, 1000);
	var time=setTimeout(function(){
		window.location.href = ROOT_PATH+'/servlet/home/homeAction_home.action';
	},s);
})

function setValue() {
	var n = $('#second').text();
	$('#second').text(n-1);
}
