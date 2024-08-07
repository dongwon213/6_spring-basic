$(document).ready(function() {
    initializeSummernote();

    // validateRequestId 버튼 클릭 이벤트 추가
    $('#validateRequestIdButton').on('click', function() {
        validateRequestId();
    });
});

function initializeSummernote() {
    $('#inquiryContent').summernote({
        tabsize: 2,
        placeholder: '내용을 입력하세요...',
        callbacks: {
            onImageUpload: function(files) {
                for (let i = 0; i < files.length; i++) {
                    uploadImage(files[i], this);
                }
            }
        }
    });
}

function uploadImage(file, editor) {
    let data = new FormData();
    data.append("file", file);
    $.ajax({
        url: '/upload/image',
        cache: false,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        data: data,
        method: "post",
        success: function(url) {
            $(editor).summernote('insertImage', url);
        },
        error: function(data) {
            console.error(data);
        }
    });
}

function toggleSidebar() {
    var sidebar = document.querySelector('.sidebar');
    sidebar.classList.toggle('expanded');
}

function validateRequestId() {
    let requestId = $('#requestId').val();
    console.log('Request ID:', requestId);  // 로그 확인

    $.get('/rest/validateRequestId', { requestId: requestId })
        .done(function(response) {
            if (response) {
                alert('유효한 요청 ID입니다.');
            } else {
                alert('존재하지 않는 요청 ID입니다.');
            }
        })
        .fail(function(error) {
            console.error('Error:', error);
            alert('요청 ID 확인 중 오류가 발생했습니다.');
        });
}
