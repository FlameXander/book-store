$(document).ready( function () {
    var table = $('#productsTable').DataTable({
        "sAjaxSource": "/market/data",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "serverSide": true,
        "aoColumns": [
            { "mData": "id"},
            { "mData": "title" },
            { "mData": "price" }
        ]
    })
});