(function () {
    let menuToggleOpen = document.getElementsByClassName('header_user')[0];
    let button = document.getElementsByClassName('openPopUp')[0];
    if (!button) {
        menuToggleOpen.addEventListener("click", menuToggleHandler, false);
        let isOpen = false;

        function menuToggleHandler() {
            let menu = document.getElementsByClassName('openMenu')[0];
            if (!isOpen) {
                menu.style.display = 'block';
                isOpen = true;
            } else {
                menu.style.display = 'none';
                isOpen = false;
            }
        }
    }
})();

//like button
(function () {
    function sendLike(link, context) {
        let button = document.getElementsByClassName('openPopUp')[0];
        if(button){
            window.location.href = "http://aristo-pets.com/#auth";
            return;
        }
        $.ajax({
            type: 'POST',
            url: link,
            contentType: "application/x-www-form-urlencoded",
            dataType: 'json',
            statusCode: {
                409: function () {
                    $(context).prev().css({
                        'background-color': '#FFC3C3',
                        'color': '#CE4646'
                    });
                    $(context).prev().text('Already in favorite');
                    $(context).prev().fadeIn(500).delay(2500).fadeOut(500);
                },
                201: function () {
                    $(context).prev().text('Added to favorite');
                    $(context).prev().fadeIn(500).delay(2500).fadeOut(500);
                }
            }
        });
    }

    let likeButton = $('.descriptionAnimal_like');
    if (likeButton) {
        likeButton.on('click', function (event) {
            event.preventDefault();
            let link = this.dataset.link;
            sendLike(link, this);
        });
    }
})();


// animal cards
(function () {
    let favCardImgArea = $('.favCardImgArea');
    let favDelete = favCardImgArea.children('.favDelete');
    if (favCardImgArea) {
        favCardImgArea.hover(function () {
            $(this).children('.favDelete').fadeIn();
        }),
            function () {
                function altFade(parent) {
                    this.children('.favDelete').fadeOut(500);
                }

                let smoothFade = altFade.bind($(this));
                setTimeout(smoothFade, 1000);
            }

        //deleting items dummy, TO BE FINISHED
        favDelete.on('click', function () {
            confirm('Are you sure you want do delete this item?');
        });
    }
})();
// 

(function () {
    let containerSlider = $('.containerSlider');
    let sliderText = $('.wrapForoverlow');
    if (containerSlider) {
        setTimeout(function () {
            containerSlider.slideDown(500, function () {
                sliderText.fadeTo(500, 1);
                $('.fotorama__stage').css('background', 'none');
            });
        }, 300);
    }
})();

// $(document).ready(function(){
//   $('.bxslider').bxSlider();
// });