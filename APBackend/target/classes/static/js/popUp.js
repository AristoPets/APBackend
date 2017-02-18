document.addEventListener("DOMContentLoaded", function(){
	var popUpOpen = document.querySelectorAll('.openPopUp');
	var popUpClose = document.querySelectorAll('.closePopUp');
	var createAccount = document.getElementById('createAccount');
	var backpopUp = document.querySelectorAll('.backpopUp');
	var authId = document.getElementById('auth');
	var registerForm = document.getElementById('regPopUp');
	if(window.location.hash == '#auth' || authId){
		openpopUp();
	}
	if(registerForm){
		openRegister();
	}

	function openpopUp(){
	    document.getElementById('popUp').classList.add("showOverlay");	
		document.body.classList.add("blockingBody");
		document.getElementById('box').classList.add("showBox");
		document.getElementById('header').classList.add("hideheader");
		if(document.getElementsByClassName('containerSlider')[0]) {
            document.getElementsByClassName('containerSlider')[0].classList.add("slider_position");
        }
        if(document.getElementsByClassName('searchMenu')[0]) {
            document.getElementsByClassName('searchMenu')[0].classList.add("margin_mod");
        }
        document.body.classList.add("body_pos");
	}

	function openRegister(){
		openpopUp();
	}
	

	for(i = 0; i < popUpOpen.length; i++){		
		popUpOpen[i].addEventListener('click', openpopUp,false);
		if(registerForm) {
            document.getElementById('box').classList.add("boxSecond");
            document.getElementsByClassName('left_side')[0].classList.add("hideFirstData");
            document.getElementsByClassName('right_side')[0].classList.add("hideFirstData");
            document.getElementsByClassName('registerForm')[0].classList.add("showSecondData");
            if(document.getElementsByClassName('containerSlider')[0]) {
                document.getElementsByClassName('containerSlider')[0].classList.add("slider_position");
            }
            if(document.getElementsByClassName('searchMenu')[0]) {
                document.getElementsByClassName('searchMenu')[0].classList.add("margin_mod");
            }
            document.body.classList.add("body_pos");
        }
	}
	for(i = 0; i < popUpClose.length; i++){		
		popUpClose[i].addEventListener('click', function(){			
			document.getElementById('popUp').classList.remove("showOverlay");
			document.getElementById('box').classList.remove("showBox");		
			document.body.classList.remove("blockingBody");
			document.getElementById('header').classList.remove("hideheader");
			document.getElementById('box').classList.remove("boxSecond");
			document.getElementsByClassName('left_side')[0].classList.remove("hideFirstData");
			document.getElementsByClassName('right_side')[0].classList.remove("hideFirstData");
			document.getElementsByClassName('registerForm')[0].classList.remove("showSecondData");
            if(document.getElementsByClassName('containerSlider')[0]) {
                document.getElementsByClassName('containerSlider')[0].classList.remove("slider_position");
            }
            if(document.getElementsByClassName('searchMenu')[0]) {
                document.getElementsByClassName('searchMenu')[0].classList.remove("margin_mod");
            }
            document.body.classList.remove("body_pos");
								
		},false);
	}
	for(i = 0; i < backpopUp.length; i++){		
		backpopUp[i].addEventListener('click', function(){
			document.getElementById('box').classList.remove("boxSecond");
			document.getElementsByClassName('left_side')[0].classList.remove("hideFirstData");
			document.getElementsByClassName('right_side')[0].classList.remove("hideFirstData");
			document.getElementsByClassName('registerForm')[0].classList.remove("showSecondData");
            if(document.getElementsByClassName('containerSlider')[0]) {
                document.getElementsByClassName('containerSlider')[0].classList.add("slider_position");
            }
            if(document.getElementsByClassName('searchMenu')[0]) {
                document.getElementsByClassName('searchMenu')[0].classList.add("margin_mod");
            }
            document.body.classList.add("body_pos");
		},false);
	}
	
	createAccount.addEventListener('click', function(){
		document.getElementById('box').classList.add("boxSecond");
		document.getElementsByClassName('left_side')[0].classList.add("hideFirstData");
		document.getElementsByClassName('right_side')[0].classList.add("hideFirstData");
		document.getElementsByClassName('registerForm')[0].classList.add("showSecondData");
        if(document.getElementsByClassName('containerSlider')[0]) {
            document.getElementsByClassName('containerSlider')[0].classList.add("slider_position");
        }
        if(document.getElementsByClassName('searchMenu')[0]) {
            document.getElementsByClassName('searchMenu')[0].classList.add("margin_mod");
        }
        document.body.classList.add("body_pos");
	}, false);
	

	var isOpen = false;
	var menu = document.getElementsByClassName('openMenu')[0];
	$(window).click(function() {
		if(menu) {
		menu.style.display='none';
			isOpen = false;
        }
	});
	$('.header_user').click(function(event){
    	event.stopPropagation();
    	if(!isOpen){
    		menu.style.display='block';
			isOpen = true;
    	}else{
    		menu.style.display='none';
			isOpen = false;
    	}
	});

	if(document.getElementById('mother') && document.getElementById('father')){
        $('.hiderrow').css({display: 'block'});
	}

    if ( document.getElementById('mother') ) {
        $('#showMore').attr("href", $('#mother').attr("data-href") );
    } else if ( document.getElementById('father') && !document.getElementById('mother') ) {
        $('#father').css({display: 'block'});
        $('#showMore').attr("href", $('#father').attr("data-href") );
    }




	$('.fa-angle-right, .fa-angle-left').click(function(event){
		event.preventDefault();
		var name = $('.vyvodok_detailsParentMale')[0].innerText;
        let mama = $('#mother').attr("data-href");
        let papa = $('#father').attr("data-href");
        $('#vyvodok_mama').attr("href", papa);
        $('#vyvodok_papa').attr("href", mama);
		if(name === "Мама"){
			$('.vyvodok_detailsParentMale')[0].innerText = 'Папа';
			$('#father').css( 'display', 'block' );
			$('#mother').css( 'display', 'none' );
            $('#showMore').attr("href", papa);
		}else{
			$('.vyvodok_detailsParentMale')[0].innerText = 'Мама';
			$('#father').css( 'display', 'none' );
			$('#mother').css( 'display', 'block' );
            $('#showMore').attr("href", mama);
		}

	})
	
 });

