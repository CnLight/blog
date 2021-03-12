function ajax(obj,url){
    $.ajax({
        type: "post",
        url: url,
        data: obj,
        async: false,
        dataType: "json",
        success: function (data) {
            if (data.flag == true) {

            } else {
                alert("error");
            }
        }
    });
}