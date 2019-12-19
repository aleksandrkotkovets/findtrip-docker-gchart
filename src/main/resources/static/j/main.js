Array.from(document.querySelectorAll('.fancy-hover'),function(el){

    el.addEventListener('mousemove',function(e){
        el.style.setProperty('--px', e.clientX - el.offsetLeft);
        el.style.setProperty('--py', e.clientY - el.offsetTop);
    });

});

$(document).ready(function(){

    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            $('.scrollup').fadeIn();
        } else {
            $('.scrollup').fadeOut();
        }
    });

    $('.scrollup').click(function(){
        $("html, body").animate({ scrollTop: 0 }, 600);
        return false;
    });

    $("#search").click(function () {
        window.open("../html/search.html", "_self");
    });

    $("#home").click(function () {
        window.open('../html/main.html', '_self');
    });

    $("#top").click(function () {
        window.open('../html/topFilms.html', '_self');
    });

    $("#wait").click(function () {
        window.open('../html/waitFilms.html', '_self');
    });

});