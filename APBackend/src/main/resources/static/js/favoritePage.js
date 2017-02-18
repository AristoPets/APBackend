(function (){
	let favCardImgArea = $('.favCardImgArea');
	let favDelete = favCardImgArea.children('.favDelete');
	favCardImgArea.hover(function(){
		$(this).children('.favDelete').fadeIn();
	},
	function(){
		function altFade(parent){
			this.children('.favDelete').fadeOut(500);
		}
		let smoothFade = altFade.bind($(this));

		setTimeout(smoothFade, 1000);
	});

	//deleting items
	favDelete.on('click', function(event){
		event.preventDefault();
		deleteCross = $(this);
		currentLink = 'http://aristo-pets.com/api/favorite/' + deleteCross.parent().parent().attr('data-uid');
		if (confirm('Are you sure you want do delete this item?')){
			$.ajax({
				url: currentLink,
				type: 'DELETE',
				success: function(){
					deleteCross.parent().parent().fadeOut(650, function(){
						$(this).remove();
					});
				}
			});
		}
	});
})();