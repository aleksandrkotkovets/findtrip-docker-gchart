$(document).ready(function(){

    $("#button_search").click(function () {
        var film = $("#film").val();

        var file = "../json/" + film + ".json";

        console.log(file);

        $.getJSON(file, function (jsonObject){

            var data = {
                name: jsonObject.name,
                year: jsonObject.year,
                director: jsonObject.director,
                during: jsonObject.during,
                rating: jsonObject.rating,
                about: jsonObject.about,
                href: jsonObject.img
            };
            var tmpl = "<div class=\"card\">\n" +
                "            <div class=\"card-media\">\n" +
                "                <img src={{href}} alt=\"\" class=\"leftimg\"/>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"card-content\">\n" +
                "                <h1 class=\"card-title\">{{name}}</h1>\n" +
                "                <div>\n" +
                "                    <small style=\"text-align: right; font-weight: 500; font-size: 15px;\">{{year}}</small> &bull;\n" +
                "                    <small style=\"text-align: right; font-weight: 500; font-size: 15px;\">{{director}}</small> &bull;\n" +
                "                    <small style=\"text-align: right; font-weight: 500; font-size: 15px;\">{{during}}</small> &bull;\n" +
                "                    <small style=\"text-align: right; font-weight: 500; font-size: 15px;\">{{rating}}</small>\n" +
                "                </div>\n" +
                "                <p>{{about}}</p>\n" +
                "            </div>\n" +
                "        </div>"

            var html = Mustache.to_html(tmpl,data);
            $("#movie").empty();
            $("#movie").append(html);

        });
    });


});

