/**
 *
 */

    $(document).ready(function () {
        $('.table .eBtn').on('click', function (event) {
            event.preventDefault();
            var href = $(this).attr('href');

            $.get(href, function (country, status) {
                $('.myForm #id').val(country.id);
                $('.myForm #country').val(country.name);

            });

            $('.myForm #exampleModal').modal();
            $('.myForm #save').click(function () {
            });
        });
    });
