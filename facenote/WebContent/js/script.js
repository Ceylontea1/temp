
function OnloadImg(url){	// img 새 창에서 크게
	var img = new Image();
	img.src = url;
	var imgWidth = 0;
	var winWidth = 0;
	var winHeight = 0;

	if(imgWidth > 500){
		winWidth = 500;
	}
	else if(imgWidth < 500){
		winWidth = img.width;
	}

	if(img.height > 500){
		winHeight = 500;  
	}
	else if(img.height < 500){
		winHeight = img.height + 30;
	}

	var OpenWindow = window.open(''+url+'','_blank', 'width='+winWidth+', height='+winHeight+', menubars=no, scrollbars=auto');
}
function readContent(url){
		window.open(''+url+'','_blank', 'width=800, height=700', 'menubars=no, scrollbars=auto');
}