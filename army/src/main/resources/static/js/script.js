document.addEventListener("DOMContentLoaded", function () {
    console.log("🚀 페이지가 로드되었습니다!");

    // 삭제 버튼 클릭 시 확인 메시지
    const deleteButtons = document.querySelectorAll(".delete-btn");
    deleteButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            const confirmDelete = confirm("정말로 삭제하시겠습니까?");
            if (!confirmDelete) {
                event.preventDefault();  // 삭제 취소 시 기본 동작 방지
            }
        });
    });
});
