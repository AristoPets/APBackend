
(function(){
	let menuToggleOpen = document.getElementsByClassName('header_user')[0];
			
	// let isOpen = false;
	// var menu = document.getElementsByClassName('openMenu')[0];
	// $(window).click(function() {
	// 	menu.style.display='none';
	// 		isOpen = false;
	// });
	// $('.header_user').click(function(event){
 //    	event.stopPropagation();
 //    	menu.style.display='block';
	// 		isOpen = true;
	// });	
	


	let scrollSideBar = document.getElementsByClassName('sideBar')[0];

	scrollSideBar.addEventListener("wheel", sideBarScroling, false);
		var srollDown = false;// this var created for sideBarScroling --- to understant in which direction we scroll

		function sideBarScroling(e){
		    let dir = e.deltaY,
		    	sideBar_height = this.clientHeight,
		    	myVwindows_height = window.innerHeight,
		    	diferents_height = sideBar_height - myVwindows_height;
		    
		    if((dir < 0) && srollDown){
		    	this.style.marginTop = "0px";
		    	srollDown = false;
		    } else if ((dir > 0) && !srollDown){
		    	this.style.marginTop = -diferents_height + "px";
		    	srollDown = true;
		    }
   			e.preventDefault();	
		}	
})();

(function(){
	let containerSlider = $('.containerSlider');
	let sliderText = $('.wrapForoverlow');
	setTimeout(function(){
		containerSlider.slideDown(500, function(){
			sliderText.fadeTo(500, 1);
		});
	}, 300);
})();