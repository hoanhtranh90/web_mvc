const Pagination = (props) => {
    console.log(props);
    props.page = props.page + 1;
    //se khong co prev va next
    let last = props.totalPage;
    let links = 3; //*2 thi ra so thằng ở giữa đang để 6 cái
    let start = ((props.page - links) > 0) ? props.page - links : 1;
    let end = ((props.page + links) < last) ? props.page + links : last;

    let html = '<ul class="pagination pagination-xs pagination-circle">';
    if (start > 1) {
        html += '<li class="page-item page-indicator"><a  class="page-link" onclick="jumpToPage(1)">1</a></li>';
        html += '<li class="page-item disabled"><span class="page-link">...</span></li>';
    }

    if (props.totalPage > 0) {
        for (let i = start; i < end; i++) {
            if (props.page == i) {
                html += '<li class="page-item active"><span class="page-link">' + i + '</span></li>';
            } else {
                html += '<li class="page-item" style="cursor:pointer" onclick="jumpToPage(' + i + ')"><span class="page-link">' + i + '</span></li>';
            }
        }
    } else {
        html += '<li class="page-item active"><span class="page-link">' + 1 + '</span></li>';
    }

    if (end < last) {
        html += '<li class="page-item disabled"><span class="page-link">...</span></li>';
        // html   += '<li onclick="jumpToPage(' + last + ')"><span class="page-link>' + last + '</span></li>';
    }
    if(props.page == last && last > 0) {
        //truong hop binh thuong
        html += '<li class="page-item active" onclick="jumpToPage(' + last + ')"><a class="page-link">' + last + '</i></a></li>';
    }
    else {
        if (props.totalPage > 0) {
            html += '<li class="page-item page-indicator" onclick="jumpToPage(' + last + ')"><a class="page-link">' + last + '</i></a></li>';

        }
    }
    html += '</ul>';

    return html;
}